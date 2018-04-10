import {Component, OnInit, ContentChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {TranslateService} from '@ngx-translate/core';
import {MessagesComponent} from './business/components/messages/messages.component';
import {DataService} from './business/services/data.service';

@Component({
  selector: 'app-root',
  template: `<app-messages>
              <app-authorization *ngIf="authorization">
              </app-authorization>
              <router-outlet *ngIf="!authorization && data.dataLoaded"></router-outlet>
            </app-messages>`
})
export class AppComponent implements OnInit {
  public authorization = false;

  constructor(private translate: TranslateService, private http: HttpClient, public data: DataService, private route: ActivatedRoute) {
    // this language will be used as a fallback when a translation isn't found in the current language
    translate.setDefaultLang('en');

    // the lang to use, if the lang isn't available, it will use the current loader to get them
    translate.use('en');
  }

  ngOnInit() {
    console.log('app init');
    if (window.location.pathname === '/authorization') {
      this.authorization = true;
    } else {
      this.data.getApplicationData();
    }

    // const sessionsRequest = this.data.getSessions().subscribe(
    //   () => {
    //     this.data.dataLoaded = true;
    //   },
    //   (error) => {
    //     console.log(this.messages);
    //     if (error.status === 401) {
    //       console.warn('Unauthorized');
    //       this.router.navigate(['/authorization']);
    //     }
    //   });
  }
}
