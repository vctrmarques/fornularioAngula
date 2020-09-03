import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  contatosUrl = 'http://localhost:8080/curso';

  categoriaUrl = 'http://localhost:8080/categoria';

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Array<any>>(this.contatosUrl);
  }

  criar(curso: any) {
    return this.http.post(this.contatosUrl, curso);
  }
}
