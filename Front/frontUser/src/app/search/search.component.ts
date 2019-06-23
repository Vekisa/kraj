import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {SearchService} from "../services/search.service";
import {BackendService} from "../services/backend.service";

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
  hiddenElements;
  extraOptions = [];
  accommodationTypes = [];

  searchForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private searchService:SearchService, private backendService: BackendService) { }

  ngOnInit() {

    this.backendService.getAllAccommodationTypes().subscribe(data =>{
      this.accommodationTypes = data;
      this.accommodationTypes.forEach(value=>{
        this.dropdownListAccommodationTypes.push({item_id: value.id, item_text: value.name});
      });
    });

    this.backendService.getAllExtraOptions().subscribe(data =>{
      this.extraOptions = data;
      this.extraOptions.forEach(value=>{
        this.dropdownListExtraOptions.push({item_id: value.id, item_text: value.name});
      });
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

    this.hiddenElements = false;
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
    });
  }
}
