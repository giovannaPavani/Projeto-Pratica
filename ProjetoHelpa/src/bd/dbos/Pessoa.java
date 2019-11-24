package bd.dbos;

public class Pessoa implements Cloneable, Comparable<Pessoa>
{
	 private int    codigo;
	 private String nome;
	 private String email;
	 private String cpf;
	 private String endereco;
	 private String senha;
	 private String telefone;
	 private String cidade;
	 private String uf;
	 
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
	 
	 public void setCidade(String cidade) throws Exception
	 {
	     if (cidade==null || cidade.equals(""))
	         throw new Exception ("Cidade não fornecida");
	
	     this.cidade = cidade;
	 }
	 
	 public void setUf(String uf) throws Exception
	 {
	     if (uf==null || uf.equals(""))
	         throw new Exception ("Uf não fornecida");
	
	     this.uf = uf;
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
	
	 public void setEndereco(String endereco) throws Exception
	 {
	     if (endereco==null || endereco.equals(""))
	         throw new Exception ("Endereço não fornecido");
	
	     this.endereco = endereco;
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
	 
	 public String getCidade ()
	 {
	     return this.cidade;
	 }
	 
	 public String getUf ()
	 {
	     return this.uf;
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
	
	 public String getEndereco ()
	 {
	     return this.endereco;
	 }
	
	 public String getSenha ()
	 {
	     return this.senha;
	 }
	
	 public Pessoa (int codigo, String nome, String email, String cpf, String endereco, String senha, String telefone, String cidade, String uf) throws Exception
	 {
	    this.setCodigo  (codigo);
	    this.setNome    (nome);
	    this.setEmail   (email);
	    this.setCpf     (cpf);
	    this.setEndereco(endereco);
	    this.setSenha   (senha);
	    this.setTelefone(telefone);
	    this.setCidade  (cidade);
	    this.setUf      (uf);
	 }
	
	 public String toString ()
	 {
	     String ret="";
	
	     ret+="Codigo: "+this.codigo+"\n";
	     ret+="Nome: "+this.nome  +"\n";
	     ret+="Email: "+this.email + "\n";
	     ret+="CPF: "+this.cpf + "\n";
	     ret+="Telefone: "+this.telefone + "\n";
	     ret+="Endereço: "+this.endereco + "\n";
	     ret+="Senha: "+this.senha + "\n";
	     ret+="Cidade: "+this.cidade + "\n";
	     ret+="UF: "+this.uf;
	
	     return ret;
	 }
	
	public int hashCode()
	{
		int ret = 1;
		ret = ret * 5 + new Integer (this.codigo).hashCode();
		ret = ret * 5 + this.nome.hashCode();
		ret = ret * 5 + this.email.hashCode();
		ret = ret * 5 + this.cpf.hashCode();
		ret = ret * 5 + this.telefone.hashCode();
		ret = ret * 5 + this.endereco.hashCode();
		ret = ret * 5 + this.senha.hashCode();
		ret = ret * 5 + this.cidade.hashCode();
		ret = ret * 5 + this.uf.hashCode();
		
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
		
		if(pes.codigo != this.codigo || !pes.nome.equals(this.nome) || !pes.email.equals(this.email) || !pes.cpf.equals(this.cpf) || 
		  !pes.telefone.equals(this.telefone) || !pes.endereco.equals(this.endereco) |
		  !pes.senha.equals(this.senha) || !pes.cidade.equals(this.cidade) || !pes.uf.equals(this.uf))
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
	
	public Pessoa(Pessoa modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception();
		this.codigo = modelo.codigo;
		this.nome = modelo.nome;
		this.email = modelo.email;
		this.cpf = modelo.cpf;
		this.endereco = modelo.endereco;
		this.senha = modelo.senha;
		this.telefone = modelo.telefone;
	}
	
	public int compareTo(Pessoa outro)
	{
		// n tem throw exception???
		if(this.codigo>outro.codigo)
			return 1;
		if(this.codigo<outro.codigo)
			return -1;
		return 0;
	}
}
