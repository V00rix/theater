import {Component, HostBinding, OnInit, Optional} from '@angular/core';
import {OverlayComponent} from '../overlay/overlay.component';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {MessagesComponent} from '../messages/messages.component';
import { HttpErrorResponse } from '@angular/common/http';
import {DataService} from '../../services/data.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @HostBinding('class.overlay') showOverlay = true;

  constructor(@Optional() private overlay: OverlayComponent,
              @Optional() private messages: MessagesComponent,
              private data: DataService,
              private router: Router,
              private http: HttpClient) {
  }

  ngOnInit() {
  }

  logout() {
    this.data.logout().subscribe(
      (res) => {
        this.navigate('authorization');
      }, (error: HttpErrorResponse) => {
        console.log(error);
        if (this.messages) {
          this.messages.showMessage({title: error.name, description: error.error.error.message, error});
        }
      });
  }

  navigate(to: string) {
    this.router.navigate([to]);
    this.close();
  }

  private close() {
    if (this.overlay) {
      this.overlay.close.emit();
    }
  }
}
