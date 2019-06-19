import { Component, OnInit } from '@angular/core';
import {Unit} from "../model";
import {UnitService} from "../services/unit.service";

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css']
})
export class UnitComponent implements OnInit {
  
  units: Unit[];

  constructor(private unitService: UnitService) { }

  ngOnInit() {
    this.unitService.allUnits().subscribe(data => {
      this.units = data;
    });
  }


  
}
