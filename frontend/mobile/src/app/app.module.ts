import {BrowserModule} from '@angular/platform-browser';
import {SharedModule} from '../../../shared/shared.module';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {DataService} from '../../../shared/business/services/data.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
