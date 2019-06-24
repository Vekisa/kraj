import { Component, OnInit } from '@angular/core';
import {Message, RegisteredUser} from "../model";
import {MessageService} from "../services/message.service";
import { Router} from '@angular/router';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  messages: Message[]=[];
  users: RegisteredUser[]=[];
  constructor(private messageService: MessageService, private  router: Router) { }

  ngOnInit() {

    this.messageService.allUsers().subscribe(data=>
      this.users=data
    )


  }

  readMessage(id: number){
    console.log(id);
    this.router.navigateByUrl('/home/readMessages/' + id);
  }

}
