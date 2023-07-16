import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Trip} from "../../../models/Trip";
import {faClose} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-trip-details',
  templateUrl: './trip-details.component.html',
  styleUrls: ['./trip-details.component.scss']
})
export class TripDetailsComponent {

  @Input()
  trip!: Trip;

  @Output()
  closeEmitter = new EventEmitter<void> ;

  protected readonly faClose = faClose;
  protected readonly close = close;
}
