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
            </app-messages>
            <!-- Confirmation -->
            <app-overlay *ngIf="showConfirmation" [noClose]="true" class="text-center" (close)="onConfirm()" (reject)="onReject()">
              <app-dialog [class]="'accent'">
                <label *ngIf="!confirmMessage">Confirm?</label>
                <label *ngIf="confirmMessage">{{confirmMessage}}</label>
              </app-dialog>
            </app-overlay>
`
})
export class AppComponent implements OnInit {
  public authorization = false;

  private actionToConfirm: (...any) => any;
  public showConfirmation = false;
  public confirmMessage: string = null;

  constructor(private translate: TranslateService, private http: HttpClient, public data: DataService, private route: ActivatedRoute) {
    // this language will be used as a fallback when a translation isn't found in the current language
    translate.setDefaultLang('en');

    // the lang to use, if the lang isn't available, it will use the current loader to get them
    translate.use('en');
  }

  ngOnInit() {
    console.log('app init');
    this.data.loadingFinished.subscribe(() => {
      this.authorization = false;
    });

    this.data.unauthorized.subscribe((message) => {
      this.authorization = true;
    });

    if (window.location.pathname === '/authorization') {
      this.authorization = true;
    } else {
      this.data.getApplicationData();
    }
  }

  public confirm(action: () => any, message: string) {
    this.showConfirmation = true;
    this.actionToConfirm = action;
    this.confirmMessage = message;
  }

  public onConfirm() {
    this.showConfirmation = false;
    this.actionToConfirm();
    this.confirmMessage = null;
  }

  public onReject() {
    this.showConfirmation = false;
    this.confirmMessage = null;
  }
}
