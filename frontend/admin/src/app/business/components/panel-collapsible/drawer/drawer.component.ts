import {Component, OnInit, Output, HostListener, EventEmitter, Input} from '@angular/core';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['../panel-collapsible.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() active = false;
  @Output() collapsed = new EventEmitter<void>();

  constructor() {
  }

  ngOnInit() {
  }

  @HostListener('click')
  collapsing() {
    this.collapsed.emit();
  }

  @HostListener('click')
  hideContent() {
  }


// // async function always returns a Promise
//   async function dramaticWelcome(): Promise<void> {
//     console.log("Hello");
//
//     for (let i = 0; i < 5; i++) {
//       // await is converting Promise<number> into number
//       const count:number = await delay(500, i);
//       console.log(count);
//     }
//
//     console.log("World!");
//   }

  // dramaticWelcome();
}
