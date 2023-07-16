import {Follower} from "./Follower";

export interface Trip {
  id: number,
  driverId: number,
  price: number,
  addressFrom: string,
  addressTo: string,
  countSeats: number,
  followers: Follower[],
  tripTime: string,
  carId: number
}
