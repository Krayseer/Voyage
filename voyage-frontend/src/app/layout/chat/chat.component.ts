import {Component} from '@angular/core';
import * as SockJS from "sockjs-client";
import * as Stomp from 'stompjs';
import {Client} from "stompjs";


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {

  socket = new SockJS("http://localhost:8080/ws")
  stompClient!: Client

  constructor() {
    this.stompClient = Stomp.over(this.socket);
    // const _this = this;
    // this.stompClient.connect({}, function (frame) {
    //   console.log('Connected: ' + frame);
    //   _this.stompClient.subscribe('/topic/public', function(message){
    //     console.log(JSON.parse(message.body));
    //   });
    // });
  }

}
