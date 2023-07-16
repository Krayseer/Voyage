import { Component } from '@angular/core';
import {BehaviorSubject, Observable, switchMap} from "rxjs";
import {TripService} from "../../services/TripService";
import {Trip} from "../../models/Trip";

@Component({
  selector: 'app-trip',
  templateUrl: './trip.component.html',
  styleUrls: ['./trip.component.scss']
})
export class TripComponent {

  public readonly trips$: Observable<Trip[]>;

  selectedTrip: Trip | null = null;

  private subject$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);

  constructor(private tripService: TripService) {
    this.trips$ = this.subject$.pipe(
      switchMap(() => this.tripService.getAllTrips())
    )
  }

  onSelectTrip(trip: Trip) {
    this.selectedTrip = trip;
  }

  onClose() {
    this.selectedTrip = null;
  }

}
