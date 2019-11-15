public class Pessoa implements Exception
{
 private int codigo;
 private String nome;
 private String email;
 private String cpf;
 private String conta;
 private String agencia;
 private String endereco;
 private String usuario;
 private String senha;
 private String telefone;
 
 public void setCodigo(int codigo) throws Exception
 {
    if (codigo <= 0)
       throw new Exception("Codigo invalido");

       this.codigo = codigo;
 }  
 
 public void setNome(String nome) throws Exception
 {
     if (nome==null || nome.equals(""))
         throw new Exception ("Nome não fornecido");

     this.nome = nome;
 }

 public void setTelefone(String telefone) throws Exception
 {
     if (telefone==null || telefone.equals(""))
         throw new Exception ("Telefone não fornecido");

     this.telefone = telefone;
 }

 public void setEmail(String email) throws Exception
 {
     if (email==null || email.equals(""))
         throw new Exception ("Email não fornecido");

     this.email = email;
 }

 public void setCpf(String cpf) throws Exception // fzr q nem no sql?
 {
     if (cpf==null || cpf.equals(""))
         throw new Exception ("Cpf não fornecido");

     this.cpf = cpf;
 }

 public void setConta(String conta) throws Exception
 {
     if (conta==null || conta.equals(""))
         throw new Exception ("Conta não fornecida");

     this.nome = nome;
 }

 public void setAgencia(String agencia) throws Exception
 {
     if (agencia==null || agencia.equals(""))
         throw new Exception ("Agência não fornecida");

     this.agencia = agencia;
 }

 public void setEndereco(String endereco) throws Exception
 {
     if (endereco==null || endereco.equals(""))
         throw new Exception ("Endereço não fornecido");

     this.endereco = endereco;
 }

 public void setUsuario(String usuario) throws Exception
 {
     if (usuario==null || usuario.equals(""))
         throw new Exception ("Usuário não fornecido");

     this.usuario = usuario;
 }

 public void setSenha(String senha) throws Exception
 {
     if (senha==null || senha.equals(""))
         throw new Exception ("Senha não fornecida");

     this.senha = senha;
 }

 public int getCodigo ()
 {
     return this.codigo;
 }

 public String getNome ()
 {
     return this.nome;
 }

 public String getTelefone ()
 {
     return this.telefone;
 }

 public String getEmail ()
 {
     return this.email;
 }
 
 public String getCpf ()
 {
     return this.cpf;
 }

 public String getConta ()
 {
     return this.conta;
 }

 public String getAgencia ()
 {
     return this.agencia;
 }

 public String getEndereco ()
 {
     return this.endereco;
 }

 public String getUsuario ()
 {
     return this.usuario;
 }

 public String getSenha ()
 {
     return this.senha;
 }

 public Pessoa(int codigo, String nome, String email,
 String cpf, String conta, String agencia, String endereco,
 private String usuario, String senha, String telefone)
 {
    this.setCodigo  (codigo);
    this.setNome    (nome);
    this.setEmail   (email);
    this.setCpf     (cpf);
    this.setConta   (conta);
    this.setAgencia (agencia);
    this.setEndereco(endereco);
    this.setUsuario (usuario);
    this.setSenha   (senha);
    this.setTelefone(telefone);
 }

 public String toString ()
 {
     String ret="";

     ret+="Codigo: "+this.codigo+"\n";
     ret+="Nome: "+this.nome  +"\n";
     ret+="Email: "+this.email + "\n";
     ret+="CPF: "+this.cpf + "\n";
     ret+="Conta: "+this.conta + "\n";
     ret+="Agência: "+this.agencia + "\n";
     ret+="Telefone: "+this.telefone + "\n";
     ret+="Endereço: "+this.endereco + "\n";
     ret+="Usuário: "+this.usuario + "\n";
     ret+="Senha: "+this.senha + "\n";

     return ret;
 }

public int hashCode()
{
int ret = 1;
ret = ret * 5 + new Interger (this.codigo).hashCode();
ret = ret * 5 + this.nome.hashCode();
ret = ret * 5 + this.email.hashCode();
ret = ret * 5 + this.cpf.hashCode();
ret = ret * 5 + this.telefone.hashCode();
ret = ret * 5 + this.conta.hashCode();
ret = ret * 5 + this.cargo.hashCode();
ret = ret * 5 + this.agencia.hashCode();
ret = ret * 5 + this.endereco.hashCode();
ret = ret * 5 + this.usuario.hashCode();
ret = ret * 5 + this.senha.hashCode();
ret = ret * 5 + new Float (this.salario).hashCode();

if(ret < 0)
ret = -ret;

return ret;
}

public boolean Equals(Object obj)
{
if(obj == this)
return true;

if(obj == null)
return false;

if(this.getClass() != obj.getClass())
return false;

Pessoa pes = (Pessoa)obj;

if(pes.cod != this.cod || pes.nome != this.nome || pes.cargo != this.cargo || pes.email != this.email || pes.cpf != this.cpf || pes.telefone != this.telefone || pes.conta != this.conta || pes.agencia != this.agencia || pes.endereco != this.endereco || pes.usuario != this.usuario || pes.senha != this.senha || pes.salario != this.salario)
return false;

return true;
}

public Object clone()
{
Pessoa ret = null;
try
{
ret = new Pessoa (this);
}
catch(Exception erro)
{} 
return ret;
}

public Pessoa(Pessoa molde) throws Exception
{
if(molde == null)
throw new Exception();

this.cod = molde.codigo;
this.nome = molde.nome;
this.email = molde.email;
this.cpf = molde.cpf;
this.cargo = molde.cargo;
this.telefone = molde.telefone;
this.conta = molde.conta;
this.agencia = molde.agencia;
this.endereco = molde.endereco;
this.usuario = molde.usuario;
this.senha = molde.senha;
this.salario = molde.salario;
}

}