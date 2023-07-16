import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CabinetComponent } from './layout/cabinet/cabinet.component';
import { RegistrationComponent } from './layout/registration/registration.component';
import { AuthorizationComponent } from './layout/authorization/authorization.component';
import { HeaderComponent } from './layout/components/header/header.component';
import {UserService} from "./services/UserService";
import { HomeComponent } from './layout/home/home.component';
import { UserCarsComponent } from './layout/user-cars/user-cars.component';
import {CarService} from "./services/CarService";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { TripComponent } from './layout/trip/trip.component';
import {TripService} from "./services/TripService";
import { TripDetailsComponent } from './layout/trip/trip-details/trip-details.component';

@NgModule({
  declarations: [
    AppComponent,
    CabinetComponent,
    RegistrationComponent,
    AuthorizationComponent,
    HeaderComponent,
    HomeComponent,
    UserCarsComponent,
    TripComponent,
    TripDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ],
  providers: [UserService, CarService, TripService],
  bootstrap: [AppComponent]
})
export class AppModule { }
