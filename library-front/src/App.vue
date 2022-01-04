<template>
  <div id="app">

    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Livraria</a>
      </div>
    </nav>
    
    <div class="container">
      <div class="row">
        <div class="col-s6">
          <h1 class="font-effect-outline">Novo Livro</h1>
         
          <form @submit.prevent="salvar">
              <label>Nome</label>
              <input type="text" placeholder="Nome" v-model="livro.nome">
              <label>Data de Lançamento</label>
              <input type="text" placeholder="Data de Lançamento" v-model="livro.dataDeLancamento">
              <label>Número de Páginas</label>
              <input type="number" placeholder="Páginas" v-model="livro.numPaginas">
              <label>Autor</label>
              <input type="text" placeholder="ID do Autor" v-model="livro.autorId">
      
              <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

          </form>
        
          <h1 class="font-effect-outline">Novo Autor</h1>

          <form @submit.prevent="salvarAutor">

            <label>Nome</label>
            <input type="text" placeholder="Nome" v-model="autor.nome">
            <label>Email</label>
            <input type="text" placeholder="Email" v-model="autor.email">
    
            <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

          </form>
        </div>
      </div>
      <div class="row">
        <div class="col 5">
          <table>

            <thead>

              <tr>
                <th>NOME</th>
                <th>DATA DE LANÇAMENTO</th>
                <th>NUMERO DE PÁGINAS</th>
                <th>AUTOR</th>
              </tr>

            </thead>

            <tbody>

              <tr v-for="livro of livros" :key="livro.id">

                <td>{{livro.nome}}</td>
                <td>{{livro.dataDeLancamento}}</td>
                <td>{{livro.numPaginas}}</td>
                <td>{{livro.autor.nome}}</td>
                <td>
                  <button @click="editar(livro)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
                  <button @click="remover(livro)" class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
                </td>

              </tr>

            </tbody>
        
          </table>
        </div>
        <div class="col 5">
          <table>

            <thead>

              <tr>
                <th>ID</th>
                <th>NOME</th>
                <th>EMAIL</th>
              </tr>

            </thead>

            <tbody>

              <tr v-for="autor of autores" :key="autor.id">

                <td>{{autor.id}}</td>
                <td>{{autor.nome}}</td>
                <td>{{autor.email}}</td>
                <td>
                  <button @click="editarAutor(autor)" class="waves-effect btn-small blue darken-1"><i class="material-icons">create</i></button>
                  <button @click="removerAutor(autor)" class="waves-effect btn-small red darken-1"><i class="material-icons">delete_sweep</i></button>
                </td>

              </tr>

            </tbody>
        
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Livro from './services/livros'
import Autor from './services/autores'

export default {
  data(){
    return {
      livro:{
        id: '',
        nome: '',
        dataDelancamento: '',
        numPaginas: '',
        autorId: ''
      },

      autor:{
        id:'',
        nome: '',
        email: ''
      },

      livros: [],
      autores:  [],
    }
  },

  mounted(){
    this.listar()
  },

   methods:{
    
    listar(){
      Livro.listar().then(resposta => {
      this.livros = resposta.data
      }),

      Autor.listar().then(resposta => {
      this.autores = resposta.data
      })
    },

    salvar(){
      if(!this.livro.id){
        Livro.salvar(this.livro).then(resposta => {
          this.livro = {}
          alert('Livro Salvo com Sucesso')
          this.resposta = resposta
          this.listar()
        }).catch(e => {
          console.log(e.response.data.error)
        })
      }else{
        Livro.salvar(this.livro).then(resposta => {
          this.livro = {}
          alert('Livro Atualizado com Sucesso')
          this.resposta = resposta
          this.listar()
        }).catch(e => {
          console.log(e.response.data.error)
        })
      }
    },

    salvarAutor(){
      if(!this.autor.id){
        Autor.salvar(this.autor).then(resposta => {
          this.autor = {}
          alert('Autor Salvo com Sucesso')
          this.resposta = resposta
          this.listar()
        }).catch(e => {
          console.log(e.response.data.error)
        })
      }else{
        Autor.salvar(this.autor).then(resposta => {
          this.autor = {}
          alert('Autor Atualizado com Sucesso')
          this.resposta = resposta
          this.listar()
        }).catch(e => {
          console.log(e.response.data.error)
       })
      }
    },

    editar(livro){
      this.livro = livro
    },

    editarAutor(autor){
      this.autor = autor
    },

    remover(livro){
      if(confirm('Deseja excluir o livro?')){
        Livro.apagar(livro).then(resposta =>{
          this.resposta = resposta
          this.listar() 
        })
      }
    },

    removerAutor(autor){
      if(confirm('Desejs excluir o Autor?')){
        Autor.apagar(autor).then(resposta =>{
          this.resposta = resposta
          this.listar()
      })
      }
    }

  }
  
}
</script>

<style>
  h1{
    font-family: "Copperplate", fantasy;
    font-size: 30px;
  }
</style>
