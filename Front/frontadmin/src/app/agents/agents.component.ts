import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Agent, ExtraOption} from "../model";
import {AgentService} from "../services/agent.service";

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.css']
})
export class AgentsComponent implements OnInit {

  agentForm: FormGroup;
  agents : Agent[];

  constructor(private agentService: AgentService, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.agentForm = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      bussinesRegistrationNumber: [''],
      description: [''],
      email: ['']
    });

    this.agentService.allAgents().subscribe(data => {
      this.agents = data;
    });
  }

  onSubmit(){
    this.agentService.createAgent(this.agentForm.value).subscribe(data =>{
      this.agentService.allAgents().subscribe(data =>{
        this.agents = data;
      })
    });
  }

}
