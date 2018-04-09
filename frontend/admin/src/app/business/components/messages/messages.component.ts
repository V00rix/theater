import {Component, OnInit} from '@angular/core';
import {Animations} from '../../../../../../shared/animations/animations';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss'],
  animations: Animations.animations
})
export class MessagesComponent implements OnInit {
  public showMessages = false;
  public messages: {title: string, description: string}[] = [];

  constructor() {
  }

  ngOnInit() {
  }

  showMessage(...messages: {title: string, description: string, error?: any}[]) {
    this.messages = messages.map(m => {
      if (m.error) {
        console.log(m.error);
      }
      return {title: m.title, description: m.description};
    });
    this.showMessages = true;
  }

  dismiss() {
    this.messages = [];
    this.showMessages = false;
  }
}
