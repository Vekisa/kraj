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

  file: File=null;
  constructor(private formBuilder: FormBuilder, private unitService: UnitService, private objectService: ObjectService, private router: Router) { }

  ngOnInit() {


    this.objectService.allObjects().subscribe(data => {
      this.objectList = data;
      console.log(data);
    });


    this.newUnitForm=this.formBuilder.group({
      object: [''],
      adults: [''],
      children: [''],
      beds: [''],
      size: [''],
      smoking: ['']
    });

    this.unit=new Unit();
    this.image=new Image();
    this.unit.image=[];
  }
  onSubmit() {
    console.log(this.newUnitForm.value);
    this.unit.size=this.newUnitForm.value.size;
    this.unit.smoking=this.newUnitForm.value.smoking;
    this.unit.adults=this.newUnitForm.value.adults;
    this.unit.children=this.newUnitForm.value.childern;
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

    this.formData.append(this.file.name, this.file);
    this.unitService.newImage(this.file).subscribe(data=>
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


}
