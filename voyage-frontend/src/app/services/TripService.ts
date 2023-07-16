import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Car} from "../models/Car";
import {Trip} from "../models/Trip";

@Injectable()
export class TripService {

  constructor(private http: HttpClient) {
  }

  public getAllTrips() {
    return this.http.get<Trip[]>("/api/trip");
  }

}
