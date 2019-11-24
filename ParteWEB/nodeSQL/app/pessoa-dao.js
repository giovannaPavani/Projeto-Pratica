class PessoaDao {

    // contrutor que vai receber uma instância da conexão do BD chamada db 
    constructor(db, emailLogado) {
        this.emailLogado = emailLogado;
        this._db = new db.Request();
    }

    lista(callback) {
        this._db.query('select * from HEntidades order by visualizacoes', function (err, recordset) {
            callback(err, recordset);
        })
    }

    buscarPorEmail(email, callback) {
        this._db.query("select * from HPessoas where email = '" + email + "'", (err, recordset) => {

            callback(err, recordset);
        })
    }

    listaDeDoacoesFeitas(callback) {
        this._db.query("select p.nome as 'nomePessoa',d.produto,d.quantidade, e.nome, e.endereco,d.entregue from HDoacoes d, HPessoas p, HEntidades e where p.email = '" + this.emailLogado + "' and p.codigo = d.codPessoa and d.codEntidade = e.codigo", function (err, recordset) {
            callback(err, recordset);
        })
    }

    informacoesSobreLogado(callback) {
        this._db.query("select email,senha,telefone,nome,endereco,cidade,UF from HPessoas where email ='" + this.emailLogado + "'", function (err, recordset) {
            callback(err, recordset);
        })
    }

    listaDoacoes(callback) {
        this._db.query('select e.codigo,d.produto from HEntidades e, HDoacoesNecessarias d where e.codigo = d.codEntidade  order by e.visualizacoes', function (err, recordset) {
            callback(err, recordset);
        })
    }




}
module.exports = PessoaDao;