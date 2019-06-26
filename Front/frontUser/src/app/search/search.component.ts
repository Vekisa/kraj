import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {SearchService} from "../services/search.service";
import {BackendService} from "../services/backend.service";
import {Unit} from "../../../../frontAgent/src/app/model";
import {Router} from "@angular/router";

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

  constructor(private formBuilder: FormBuilder, private searchService:SearchService, private backendService: BackendService, private router: Router) { }

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
      console.log("preuzeo2");
      console.log(data);
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
    console.log(this.searchForm.value.accTypes);
    this.searchService.search(this.searchForm.value.city,this.searchForm.value.checkInDate, this.searchForm.value.checkOutDate, this.searchForm.value.guests,this.searchForm.value.accTypes,
      this.searchForm.value.category,this.searchForm.value.distance,this.searchForm.value.additionalTypes).subscribe( data =>{
      console.log("DATA: ");
      console.log(data);
      this.units = data;
    });
  }

  showUnit(id:number){
    console.log("ID: " +id);
    this.router.navigateByUrl('/objects/'+ id);
  }
}
