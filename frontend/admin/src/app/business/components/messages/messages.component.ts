import {Component, OnInit} from '@angular/core';
import {Animations} from '../../../../../../shared/animations/animations';
import {Error} from '../../domain/error';
import {DataService} from '../../services/data.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss'],
  animations: Animations.animations
})
export class MessagesComponent implements OnInit {
  public showMessages = false;
  public messages: Error[] = [];

  constructor(public data: DataService) {
  }

  ngOnInit() {
    console.log('messages');
    this.data.errorOccurred.subscribe((error: Error) => {
      this.showMessage(error);
    });
  }

  showMessage(...messages: Error[]) {
    console.log('messaging');
    this.messages = messages.map(m => {
      if (m.error) {
        console.log(m.error.error.message);
      }
      return {title: m.title, description: m.error.error.message};
    });
    this.showMessages = true;
  }

  dismiss() {
    this.messages = [];
    this.showMessages = false;
  }
}
