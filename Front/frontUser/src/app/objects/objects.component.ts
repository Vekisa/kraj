import {Component, Input, OnInit} from '@angular/core';
import {BackendService} from '../services/backend.service';
import {ActivatedRoute} from '@angular/router';
import {Reservation, Unit} from '../../../../frontAgent/src/app/model';
import {AuthService} from '../services/auth.service';
import {Plan} from '../model';
import {Form, FormBuilder, FormGroup} from "@angular/forms";
import {ReservationService} from "../services/reservation.service";

@Component({
  selector: 'app-objects',
  templateUrl: './objects.component.html',
  styleUrls: ['./objects.component.css']
})
export class ObjectsComponent implements OnInit {
  @Input() id: number;
  title = 'CodeSandbox';
  currentRate = 1;
  unit: Unit;
  extraOptions: any;
  isLoggedIn = false;
  plans: Plan[];
  comments: any[];
  pomId : number;
  commentForm: FormGroup;
  reserveForm: FormGroup;
  reservation : Reservation;

  showIcon = false;
  available = true;
  start: Date;
  end: Date;

  pom = [];
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};

  price = 0;


  constructor(private backendService: BackendService, private activatedRoute: ActivatedRoute, private authService: AuthService, private formBuilder: FormBuilder, private reservationS : ReservationService) { }

  ngOnInit() {
    this.start = new Date('');
    this.end = new Date('');
    this.reservation = new Reservation();
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

    this.pom = [];
    this.authService.roles().subscribe(data => {
      this.isLoggedIn = true;
    } , err => {
      this.isLoggedIn = false;
    });


    this.activatedRoute.params.subscribe(data => {
      this.backendService.getUnit(data[ 'id' ]).subscribe(data => {
        this.unit = data;
        this.pomId = data['id'];
        console.log(data);
      });

      this.backendService.extraOptionsOfObject(data['id']).subscribe(data =>{
        this.extraOptions = data;
        console.log(data);
        this.extraOptions.forEach(value=>{
          this.pom.push({item_id: value.id, item_text: value.name});
          console.log("ubacio " + value.name);
        });
        this.dropdownList = this.pom;
      });

      this.backendService.getPlanOfUnit(data['id']).subscribe(data => {
        this.plans = data;
        console.log(data);
      });

      this.backendService.getComments(data['id']).subscribe(data => {
        this.comments = data;
        console.log("COMMENTS: ");
        console.log(data);
      });

    });

    this.commentForm = this.formBuilder.group({
      text: ['']
    });

    this.reserveForm = this.formBuilder.group({
      extra_options:[''],
      price:[''],
      checkInDate:[''],
      checkOutDate:['']
    });
  }
  onSubmit() {
    this.backendService.createComment(this.pomId,this.commentForm.value).subscribe(data =>{
      console.log(data);
    });
  }

  onItemSelect(item: any) {
    console.log(item);
  }

  onSelectAll(items: any) {
    console.log(items);
  }

  onSubmitReserve(){
    console.log('reserve');
    this.reservation.start = this.reserveForm.value.checkInDate;
    this.reservation.end = this.reserveForm.value.checkOutDate;
    this.reservation.unit = this.unit;
    this.reservationS.reserve(this.reservation).subscribe(data=>{
      console.log(data);
    });
  }

  changed(){
    console.log(this.reserveForm.value.checkInDate);
    if(this.reserveForm.value.checkInDate!='' && this.reserveForm.value.checkOutDate!=''){
      this.reservation.start = this.reserveForm.value.checkInDate;
      this.reservation.end = this.reserveForm.value.checkOutDate;
      this.reservation.unit =  this.unit;
      this.reservationS.checkUnit(this.reservation).subscribe(data =>{
        if(data){
          this.showIcon = true;
          this.available = true;
        }else{
          this.showIcon = true;
          this.available = false;
        }

        this.reservationS.getPrice(this.reservation).subscribe(data =>{
          this.price = data;
        });
      });



    }
  }

}
