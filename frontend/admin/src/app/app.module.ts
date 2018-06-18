import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {DatePipe} from '@angular/common';


import {AppComponent} from './app.component';
import {PanelCollapsibleComponent} from './business/components/panel-collapsible/panel-collapsible.component';
import {AuthorizationComponent} from './pages/authorization/authorization.component';
import {HomeComponent} from './pages/home/home.component';
import {ListComponent} from './business/components/list/list.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatFormFieldModule,
  MatInputModule,
  MatProgressBarModule,
  MatNativeDateModule
} from '@angular/material';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {DataService} from './business/services/data.service';
import {DrawerComponent} from './business/components/panel-collapsible/drawer/drawer.component';
import {ContentComponent} from './business/components/panel-collapsible/content/content.component';
import {DialogComponent} from './business/components/dialog/dialog.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
/* i18n */
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {registerLocaleData} from '@angular/common';
import localeRu from '@angular/common/locales/ru';
import localeEn from '@angular/common/locales/en';
import {MenuComponent} from './business/components/menu/menu.component';
import {OverlayComponent} from './business/components/overlay/overlay.component';
import {SessionsComponent} from './pages/sessions/sessions.component';
import {MessagesComponent} from './business/components/messages/messages.component';
import {ViewersComponent} from './pages/viewers/viewers.component';
import {BasicPageComponent} from './business/components/basic-page/basic-page.component';
import {KeysPipe} from "./business/pipes/keysPipe";

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

registerLocaleData(localeRu, 'ru');
registerLocaleData(localeEn, 'en');

@NgModule({
  declarations: [
    AppComponent,
    PanelCollapsibleComponent,
    AuthorizationComponent,
    HomeComponent,
    ListComponent,
    DrawerComponent,
    ContentComponent,
    DialogComponent,
    MenuComponent,
    OverlayComponent,
    SessionsComponent,
    MessagesComponent,
    ViewersComponent,
    BasicPageComponent,
    KeysPipe
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatProgressBarModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    HttpClientModule,
    AppRoutingModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    })
  ],
  providers: [DataService, DatePipe,
    {provide: MAT_DATE_LOCALE, useValue: 'ru-RU'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
