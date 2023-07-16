import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormGroup} from "@angular/forms";
import {User} from "../models/User";
import {Car} from "../models/Car";

@Injectable()
export class CarService {

  constructor(private http: HttpClient) {
  }

  public getUserCars() {
    return this.http.get<Car[]>("/api/car");
  }

  public addCar(userForm: FormGroup){
    return this.http.post('/api/car', userForm.value, {
      headers: {'Content-Type': 'application/json'}
    });
  }

  public deleteCar(id: number) {
    return this.http.delete(`/api/car/${id}`, {
      headers: {'Content-Type': 'application/json'}
    });
  }
}
