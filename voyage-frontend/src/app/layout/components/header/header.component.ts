import { Component } from '@angular/core';
import {UserService} from "../../../services/UserService";
import {BehaviorSubject, Observable, switchMap} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  public readonly imageSource$: Observable<string>;

  private subject$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);

  constructor(private userService: UserService) {
    this.imageSource$ = this.subject$.pipe(
      switchMap(() => this.userService.getAccountPhoto())
    );
  }

}
