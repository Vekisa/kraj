import { Component, OnInit } from '@angular/core';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-reserved',
  templateUrl: './reserved.component.html',
  styleUrls: ['./reserved.component.css'],
  providers: [NgbRatingConfig]
})
export class ReservedComponent implements OnInit {
  currentRate = 1;

  constructor(config: NgbRatingConfig) {}

  ngOnInit() {
  }

}
