import {Component, OnInit, Input, Optional} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {MessagesComponent} from '../messages/messages.component';

@Component({
  selector: 'app-basic-page',
  templateUrl: './basic-page.component.html',
  styleUrls: ['./basic-page.component.scss']
})
export class BasicPageComponent implements OnInit {
  @Input() public menu = false;
  @Input() public footer = true;
  @Input() public back: { label: string, link: string };
  @Input() public h: { label: string, type: string }[];

  public menuShown = false;

  public headers = {h1: [], h2: [], h3: [], h4: [], h5: []};

  constructor(@Optional() public messages: MessagesComponent, public data: DataService,
              private router: Router) {
  }

  ngOnInit() {
    this.h.forEach(h => {
      switch (h.type) {
        case 'h1':
          this.headers.h1.push(h);
          break;
        case 'h2':
          this.headers.h2.push(h);
          break;
        case 'h3':
          this.headers.h3.push(h);
          break;
        case 'h4':
          this.headers.h4.push(h);
          break;
        case 'h5':
          this.headers.h5.push(h);
          break;
      }
    });
  }

  navigate(to: string) {
    this.data.loadingFinished.next();
    this.router.navigate([to]);
  }
}
