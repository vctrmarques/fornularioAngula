import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  cursos: Array<any>;
  curso: any;
  
  categorias: any;

  constructor(private service: AppService) {}

  ngOnInit() {
    this.curso = {};
    this.categorias = {};

    this.service.listar()
      .subscribe(resposta => this.cursos = resposta);

  }

  criar(frm: FormGroup) {
    this.service.criar(this.curso).subscribe(resposta => {
      this.cursos.push(resposta);

      frm.reset();
    });
  }
}
