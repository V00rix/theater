import {NgModule} from '@angular/core';

import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {NotFoundComponent} from './pages/not-found/not-found.component';
import {ConfirmationComponent} from './pages/confirmation/confirmation.component';
import {ContactsComponent} from './pages/contacts/contacts.component';
import {PerformanceComponent} from './pages/performance/performance.component';
import {PerformancesComponent} from './pages/performances/performances.component';
import {SessionComponent} from './pages/session/session.component';
import {SessionsComponent} from './pages/sessions/sessions.component';
import {SuccessComponent} from './pages/success/success.component';
import {ViewerComponent} from './pages/viewer/viewer.component';
import {CheckoutComponent} from './pages/checkout/checkout.component';

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'confirmation', component: ConfirmationComponent},
  {path: 'contacts', component: ContactsComponent},
  {path: 'performance', component: PerformanceComponent},
  {path: 'performances', component: PerformancesComponent},
  {path: 'session', component: SessionComponent},
  {path: 'sessions', component: SessionsComponent},
  {path: 'success', component: SuccessComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'viewer', component: ViewerComponent},
  {path: '**', component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes
  )],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
