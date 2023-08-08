import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CabinetComponent} from "./layout/cabinet/cabinet.component";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {TokenInterceptor} from "./interceptors/TokenInterceptor";
import {RegistrationComponent} from "./layout/registration/registration.component";
import {AuthorizationComponent} from "./layout/authorization/authorization.component";
import {HomeComponent} from "./layout/home/home.component";
import {UserCarsComponent} from "./layout/user-cars/user-cars.component";
import {TripComponent} from "./layout/trip/trip.component";
import {ChatComponent} from "./layout/chat/chat.component";

const routes: Routes = [

  { path: '', component: HomeComponent},
  { path: 'cabinet', component: CabinetComponent },
  { path: 'register', component: RegistrationComponent},
  { path: 'auth', component: AuthorizationComponent },
  { path: 'user-cars', component: UserCarsComponent },
  { path: 'trip', component: TripComponent },
  { path: 'chat', component: ChatComponent }

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ]
})
export class AppRoutingModule {
}
