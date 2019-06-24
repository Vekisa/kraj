import { Component, OnInit } from '@angular/core';
import {Message} from "../model";
import {MessageService} from "../services/message.service";
import { Router} from '@angular/router';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  messages: Message[]=[];
  constructor(private messageService: MessageService, private  router: Router) { }

  ngOnInit() {

    this.messageService.allMessages().subscribe(data=>{
      this.messages=data;
    })
  }

  readMessage(i: number){
    console.log(i);
    this.router.navigateByUrl('/home/readMessages/' + this.messages[i].registeredUser.id);
  }

  getDate(i: number){
    let d: Date= new Date(this.messages[i].postingDate);
    return d.getDate()+"-"+d.getMonth()+"-" + d.getFullYear();
  }
}
