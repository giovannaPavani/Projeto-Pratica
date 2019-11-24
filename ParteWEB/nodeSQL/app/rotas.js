var PessoaDao = require('../app/pessoa-dao');
var EntidadeDao = require('../app/entidade-dao');
var conexao = require('../config/custom-mssql');
const session = require('express-session');
var senhaLocal;
var emailLogado = "vazio";
var pessoaDao = new PessoaDao(conexao, emailLogado);

var sess;

function execSQL(sql, resposta) {
    global.conexao.request()
        .query(sql)
        .then(resultado => resposta.json(resultado.recordset))
        .catch(erro => resposta.json(erro));
}

module.exports = (app) => {

    app.post('/', function (req, res) {

        const doacao = req.body.doacao;
        const entidade = req.body.codDaEntidade;
        const qtd = req.body.quantidade;

        conexao.query(`select codigo from HPessoas where email='${emailLogado}'`, (err, result) => {
            var codLogado = result.recordset[0].codigo;
            execSQL(`Insert into HDoacoes values (${codLogado},'${doacao}',${entidade},GETUTCDATE(),'N','${qtd}')`, res);
        });
        execSQL(`UPDATE HEntidades set visualizacoes=visualizacoes+1 where codigo = '${entidade}'`, res);

        res.redirect('/#entidades');
    });


    app.get('/', function (req, res) {

        sess = req.session;
        sess.email;
        sess.senha;
        sess.codigo;

        pessoaDao.lista(function (erro, resultados) {
            pessoaDao.listaDoacoes(function (erro, resultados2) {
                pessoaDao.informacoesSobreLogado(function (erro, resultados3) {
                    res.render('paginas/home', {
                        lista: resultados["recordset"],
                        listaDoacoes: resultados2["recordset"],
                        informacoesSobreLogado: resultados3["recordset"]
                    })
                })
            });
        });
    });

    app.get('/cadastro', function (req, resp) {
        if (typeof sess.email == "undefined") {

            resp.render("paginas/cadastro");
        }
        else {
            resp.redirect("/#");
        }

    });

    app.get('/pesquisar/:busc', function (req, resp) {
        var busc = req.params.busc;
        console.log("Busca_sp '" + busc + "'");
        execSQL("Busca_sp '" + busc + "'", resp);
    });

    app.post('/cadastro', function (req, resp) {
        const nome = req.body.nome.substring(0, 50);
        const email = req.body.email.substring(0, 50);
        const endereco = req.body.endereco.substring(0, 100);
        const cidade = req.body.cidade.substring(0, 40);
        const uf = req.body.uf.substring(0, 2);
        const senha = req.body.senha.substring(0, 15);
        const telefone = req.body.telefone.substring(0, 14);

        conexao.query(`SELECT * FROM HPessoas where email = '${email}'`, (err, result) => {
            if (result.rowsAffected == 0) {
                execSQL(`INSERT INTO HPessoas VALUES('${nome}','${email}','','${endereco}','${cidade}','${uf}','${senha}','${telefone}')`, resp);
                resp.render("paginas/login");
            } else
                resp.render("paginas/cadastro");
        });
    });

    app.get('/login', function (req, resp) {
        sess = req.session;
        if (typeof sess.email == "undefined") {
            resp.render("paginas/login");
        }
        else {
            resp.redirect("/");
        }
    });

    app.post('/login', function (req, resp) {
        sess = req.session;

        emailLogado = req.body.email.substring(0, 50);
        senhaLocal = req.body.senha.substring(0, 15);

        pessoaDao = new PessoaDao(conexao, emailLogado);
        pessoaDao.buscarPorEmail(emailLogado, function (erro, resultados) {

            if (erro) {
                console.log("erro no login");
            }
            else if (resultados.recordset.length != 0 && resultados.recordset[0].senha == senhaLocal) {
                sess.email = emailLogado;
                sess.senha = senhaLocal;
                emailLogado = sess.email;

                pessoaDao.lista(function (erro, resultados) {
                    pessoaDao.listaDoacoes(function (erro, resultados2) {
                        pessoaDao.informacoesSobreLogado(function (erro, resultados3) {
                            resp.render('paginas/home', {
                                lista: resultados["recordset"],
                                listaDoacoes: resultados2["recordset"],
                                informacoesSobreLogado: resultados3["recordset"]
                            })
                        })
                    });
                });
            }
            else {
                resp.redirect('/login');
            }
        });
    });

    app.get('/sair', (req, res) => {
        sess = req.session;
        if (typeof sess.email == "undefined") {
            res.render("paginas/login");
        }
        else {
            req.session.destroy((err) => {
                if (err) {
                    return console.log(err);
                }
                emailLogado = "vazio";
                senhaLocal = undefined;
                pessoaDao = new PessoaDao(conexao, emailLogado);
                pessoaDao.lista(function (erro, resultados) {
                    pessoaDao.listaDoacoes(function (erro, resultados2) {
                        pessoaDao.informacoesSobreLogado(function (erro, resultados3) {
                            res.render('paginas/home', {
                                lista: resultados["recordset"],
                                listaDoacoes: resultados2["recordset"],
                                informacoesSobreLogado: resultados3["recordset"]
                            })
                        })
                    });
                });
            });
        }
    });

    app.get('/editarInformacoes', function (req, resp) {
        sess = req.session;
        if (typeof sess.email == "undefined") {
            resp.render("paginas/login");
        }
        else {
            pessoaDao = new PessoaDao(conexao, emailLogado);
            pessoaDao.informacoesSobreLogado(function (erro, resultados) {
                console.log(resultados);
                resp.render('paginas/editarInformacoes', {
                    informacoesSobreLogado: resultados["recordset"]
                });
            });
        }
    });

    app.post('/editarInformacoes', function (req, resp) {
        const nome = req.body.nome.substring(0, 50);
        const email = req.body.email.substring(0, 50);
        const endereco = req.body.endereco.substring(0, 100);
        const cidade = req.body.cidade.substring(0, 40);
        const uf = req.body.uf.substring(0, 2);
        const telefone = req.body.telefone.substring(0, 14);

        pessoaDao = new PessoaDao(conexao, emailLogado);
        pessoaDao.buscarPorEmail(emailLogado, function (erro, resultados) {
            if (erro) {
                console.log("Erro para Atualizar");
            }
            if (emailLogado != email) {
                conexao.query(`SELECT * FROM HPessoas where email = '${email}'`, (err, result) => {
                    console.log(result);
                    if (result.rowsAffected == 0) {
                        execSQL(`update HPessoas set email='${email}',nome='${nome}',endereco='${endereco}',telefone='${telefone}',cidade='${cidade}',UF='${uf}' where email = '${emailLogado}'`, resp);
                        emailLogado = email;
                        resp.redirect('/editarInformacoes');
                    } else
                        console.log("erro");
                });
            }
            else {
                execSQL(`update HPessoas set nome='${nome}',endereco='${endereco}',telefone='${telefone}',cidade='${cidade}',UF='${uf}' where email = '${email}'`, resp);
                resp.redirect('/consulta');
            }
        });
    });

    app.get('/editarSenha', function (req, resp) {
        sess = req.session;
        if (typeof sess.email == "undefined") {
            resp.render("paginas/login");
        }
        else {
            pessoaDao = new PessoaDao(conexao, emailLogado);
            pessoaDao.informacoesSobreLogado(function (erro, resultados) {
                resp.render('paginas/editarSenha', {
                    informacoesSobreLogado: resultados["recordset"]
                });
            });
        }
    });

    app.post('/editarSenha', function (req, resp) {
        const nsenha1 = req.body.nsenha1.substring(0, 15);
        const nsenha2 = req.body.nsenha2.substring(0, 15);
        const senha = req.body.senha.substring(0, 15);
        
        pessoaDao = new PessoaDao(conexao, emailLogado);
        pessoaDao.buscarPorEmail(emailLogado, function (erro, resultados) {
            console.log(resultados.recordset[0].senha);
            console.log(senha);
            console.log(nsenha1);

            if (erro) {
                console.log("erro no login");
            }
            else if (resultados.recordset[0].senha == senha && nsenha1 == nsenha2) {
                console.log("entou1");
            sess.senha = senha;
            senhaLocal = senha;
            execSQL(`update HPessoas set senha='${nsenha1}' where email = '${emailLogado}'`, resp);
            resp.redirect('/editarSenha');
            }
            else{
                console.log("entou2");
            execSQL(`select descricao from Erro where nome = 'senha'`, resp);
            }

        });
    });

    app.get('/consulta', function (req, resp) {
        sess = req.session;
        if (typeof sess.email == "undefined") {
            resp.render("paginas/login");
        }
        else {
            pessoaDao = new PessoaDao(conexao, emailLogado);
            pessoaDao.informacoesSobreLogado(function (erro, resultados) {
                pessoaDao.listaDeDoacoesFeitas(function (erro, resultados2) {
                    resp.render('paginas/consultaDoacoes', {
                        informacoesSobreLogado: resultados["recordset"],
                        listaDeDoacoesFeitas: resultados2["recordset"]

                    });
                });

            });
        }
    });
}

