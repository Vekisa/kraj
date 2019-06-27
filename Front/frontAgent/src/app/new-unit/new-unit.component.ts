import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {UnitService} from "../services/unit.service";
import {Object, Unit, Image} from "../model";
import {ObjectService} from "../services/object.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-unit',
  templateUrl: './new-unit.component.html',
  styleUrls: ['./new-unit.component.css']
})
export class NewUnitComponent implements OnInit {
  selectedImage: File=null;
  newUnitForm: FormGroup;

  objectList: Object[];
  logo: any;
  unit: Unit;
  image: Image;
  formData: FormData = new FormData();
  slike: File[]=[];
  file: File=null;

  nn: number;
  constructor(private formBuilder: FormBuilder, private unitService: UnitService, private objectService: ObjectService, private router: Router) { }

  ngOnInit() {


    this.objectService.allObjects().subscribe(data => {
      this.objectList = data;
      console.log(data);
    });


    this.newUnitForm=this.formBuilder.group({
      object: [''],
      person: [''],
      beds: [''],
      cancellation: ['']
    });

    this.unit=new Unit();
    this.image=new Image();
    this.unit.image=[];
    this.nn=0;
  }
  onSubmit() {
    for(let l=0; l<this.slike.length; l++){
      if(this.slike[l]!=null && l<this.slike.length) {
        this.formData.append(this.slike[l].name, this.slike[l]);
        this.unitService.newImage(this.slike[l]).subscribe(data => {
            this.unit.image.push(data)
            console.log(data);
          console.log("unti l " + this.unit.image.length + " n " +this.nn);
            if(this.unit.image.length==this.slike.length-this.nn){
              this.createUnit();
            }
          }
        )
      }
    }
  }

  createUnit(){
    console.log(this.newUnitForm.value);
    this.unit.person=this.newUnitForm.value.person;
    this.unit.cancellation=this.newUnitForm.value.cancellation;
    this.unit.beds=this.newUnitForm.value.beds;
    for(let object of this.objectList){
      if(object.id==this.newUnitForm.value.object){
        this.unit.object=object;
      }
    }
    console.log(this.unit);
    this.unitService.newUnit(this.unit).subscribe(data=>
      {this.router.navigateByUrl('/home/newPlan/' + data.id);
        console.log(data)}
    );
  }

  createObject(){
    this.router.navigateByUrl('/home/newObject');
  }

  onSelectedImage(event){

    this.file=<File> event.target.files[0];
    this.slike.push(this.file);
    /* this.formData.append(this.file.name, this.file);
   * this.unitService.newImage(this.file).subscribe(data=>
     { this.unit.image.push(data);
       console.log(data)}
       )
     /*const reader = new FileReader();
     reader.onload = (eve: any) => {
       this.logo = eve.target.result;
       let pom= eve.target.result;
       let pomS= pom.split(",");
       this.image.source= pomS[1];
       this.unit.image.push(this.image);
       console.log(this.image.source);

       this.unitService.newImage(this.image.source).subscribe(data=>
       console.log(data))
     };
     reader.readAsDataURL(event.target.files[0]);

    /* let fileList: FileList = event.target.files;
     if(fileList.length > 0) {
       let file: File = fileList[0];
       let formData:FormData = new FormData();
       formData.append('uploadFile', file, file.name);
       this.image.source=this.logo;
       console.log(this.logo);
       this.unit.image.push(this.image);

     }*/
  }

  /*onUpload(){
    const fd=new FormData();
    fd.append('image', this.selectedImage, this.selectedImage.name);
    this.image.source=fd;
    this.unit.image.push(this.image);
    console.log(this.image);
  }*/

  ukloni(i: number){
    this.slike[i]=null;
    this.nn++;
  }
}
