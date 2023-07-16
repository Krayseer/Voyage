import {Component} from '@angular/core';
import {BehaviorSubject, Observable, switchMap, take} from "rxjs";
import {UserService} from "../../services/UserService";
import {User} from "../../models/User";
import {faPlus} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-cabinet',
  templateUrl: './cabinet.component.html',
  styleUrls: ['./cabinet.component.scss']
})
export class CabinetComponent {

  public readonly user$: Observable<User>;

  public readonly imageSource$: Observable<string>;

  private subject$: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);

  constructor(private userService: UserService) {
    this.user$ = this.userService.getAccountInfo();
    this.imageSource$ = this.subject$.pipe(
      switchMap(() => this.userService.getAccountPhoto())
    );
  }

  selectPhoto($event: any) {
    const file = $event.target.files[0];
    this.userService.uploadAccountPhoto(file)
      .pipe(take(1))
      .subscribe(() => this.subject$.next())
  }

  protected readonly localStorage = localStorage;
  showUploadIcon = true;
  protected readonly faPlus = faPlus;
}
