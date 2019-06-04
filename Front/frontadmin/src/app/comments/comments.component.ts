import { Component, OnInit } from '@angular/core';
import {CommentsService} from "../services/comments.service";
import {Commenta} from "../model";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments: Commenta[];

  constructor(private commentService : CommentsService) { }

  ngOnInit() {
    this.commentService.allComments().subscribe(data =>{
      this.comments = data;
    })
  }

  approve(id: number){
    this.commentService.approve(id).subscribe(data=>{
      this.commentService.allComments().subscribe(data=>{
        this.comments = data;
      });
    });
  }

}
