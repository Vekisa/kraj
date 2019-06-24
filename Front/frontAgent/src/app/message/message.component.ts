import {Component, Input, OnInit} from '@angular/core';
import {Message} from "../model";
import {MessageService} from "../services/message.service";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  @Input() id: any;

  messages: Message[]=[];
  message: Message;
  constructor(private messageService: MessageService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(data=>{
      console.log(data['id']);
      this.messageService.allMessagesFromUser(data['id']).subscribe(data=>
      {this.messages=data;
      } )
    });
    this.message=new Message();
  }

  getDate(i: number){
    let d: Date= new Date(this.messages[i].postingDate);
    return d.getDate()+"-"+d.getMonth()+"-" + d.getFullYear();
  }

  sendMessage(){
    this.message.postingDate=new Date();
    this.message.registeredUser=this.messages[0].registeredUser;
    this.messageService.newMessage(this.message).subscribe(data=>
    console.log(data));
    this.router.navigateByUrl('/home/showMessages'+this.messages[0].registeredUser.id);
  }
}
