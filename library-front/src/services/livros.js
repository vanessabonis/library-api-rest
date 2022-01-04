import { http } from './config'

export default {
    //GET
    listar:() => {
        return http.get('livros')
    },

    //POST
    salvar: (livro) => {
        return http.post('livros', livro)
    },

    //PUT
    atualizar:(livro) => {
        return http.put('livros/' + livro.id, livro)
    },

    //DELETE
    apagar:(livro) => {
        return http.delete("livros/" + livro.id, livro)
    }

}