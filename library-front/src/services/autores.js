import { http } from './config'

export default {
    //GET
    listar:() => {
        return http.get('autores')
    },
    //POST
    salvar: (autor) => {
        return http.post('autores', autor)
    },
    //PUT
    atualizar:(autor) => {
        return http.put('autores/' + autor.id, autor)
    },
    //DELETE
    apagar:(autor) => {
        return http.delete('autores/' + autor.id, autor)
    }
}