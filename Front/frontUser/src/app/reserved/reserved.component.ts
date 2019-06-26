import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reserved',
  templateUrl: './reserved.component.html',
  styleUrls: ['./reserved.component.css']
})
export class ReservedComponent implements OnInit {
  currentRate = 1;

  constructor() { }

  ngOnInit() {
  }

}
