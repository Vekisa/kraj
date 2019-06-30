import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {SearchService} from "../services/search.service";
import {BackendService} from "../services/backend.service";
import {Unit} from "../../../../frontUser/src/app/model";
import {Router} from "@angular/router";
import {RatingService} from "../services/rating.service";
import {Rtng} from "../model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  dropdownListExtraOptions = [];
  dropdownListAccommodationTypes = [];
  dropdownListCategory = [];
  selectedItems = [];
  dropdownSettings = {};
  hiddenElements = true;
  extraOptions : any[];
  accommodationTypes : any[];
  pom1 = [];
  pom2 = [];
  searchForm: FormGroup;
  units: Unit[];
  unitsPom : Unit[];
  ids: number[];
  list : Rtng[];

  constructor(private formBuilder: FormBuilder, private searchService:SearchService, private backendService: BackendService, private router: Router, private ratingService: RatingService) { }

  ngOnInit() {
    this.pom1 = [];
    this.pom2 = [];

    this.backendService.getAllAccommodationTypes().subscribe(data =>{
      this.accommodationTypes = data;

      this.accommodationTypes.forEach(value=>{
        this.pom1.push({item_id: value.id, item_text: value.name});
      });
      this.dropdownListAccommodationTypes = this.pom1;
    });

    this.backendService.getAllExtraOptions().subscribe(data =>{
      this.extraOptions = data;
      this.extraOptions.forEach(value=>{
        this.pom2.push({item_id: value.id, item_text: value.name});
      });

      this.dropdownListExtraOptions = this.pom2;
    });

    this.dropdownListCategory = [
      {item_id : 1, item_text : '1'},
      {item_id : 2, item_text : '2'},
      {item_id : 3, item_text : '3'},
      {item_id : 4, item_text : '4'},
      {item_id : 5, item_text : '5'},
    ]


    this.searchForm = this.formBuilder.group({
      city: [''],
      checkInDate: [''],
      checkOutDate:[''],
      guests:[''],
      accTypes:[''],
      category:[''],
      distance:[''],
      additionalTypes:['']
    });

    console.log("ovoo:" + this.hiddenElements);

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  hideAdditionalElements(){
    this.hiddenElements = true;
  }

  showAdditionalElements(){
    this.hiddenElements = false;
  }

  onSubmit(){
    this.ids = [];
    console.log(this.searchForm.value.accTypes);
    this.searchService.search(this.searchForm.value.city,this.searchForm.value.checkInDate, this.searchForm.value.checkOutDate, this.searchForm.value.guests,this.searchForm.value.accTypes,
      this.searchForm.value.category,this.searchForm.value.distance,this.searchForm.value.additionalTypes).subscribe( data =>{
      console.log("DATA: ");
      console.log(data);
      this.unitsPom = data;

      this.unitsPom.forEach(value =>{
        this.ids.push(value.object.id);
      });

      /*this.ratingService.getRatings(this.ids).subscribe(data =>{
        this.list = data;
        this.list.forEach(rtng =>{
          this.unitsPom.forEach(unit =>{
            if(unit.object.id = rtng.id){
              unit.rating = rtng.mark;
            }
          })
        });
        this.units = this.unitsPom;
      });*/

      this.units = this.unitsPom;
    });
  }

  showUnit(id:number){
    console.log("ID: " +id);
    this.router.navigateByUrl('objects/'+ id);
  }

  sortByPrice(){

  }

  sortByDistance(){

  }

  sortByRating(){

  }

  sortByCategory(){
    let sortedArray: Unit[] = this.units.sort((obj1, obj2) => {
      if (obj1.object.category > obj2.object.category) {
        return 1;
      }

      if (obj1.object.category > obj2.object.category) {
        return -1;
      }

      return 0;
    });
    this.units = sortedArray;
  }
}
