package bd.dbos;

public class Entidade implements Cloneable, Comparable<Entidade>
{
	 private int    codigo;
	 private String nome;
	 private String cnpj;
	 private String endereco;
	 private String email;
	 private String telefone;
	 private String usuario;
	 private String senha;
	 private int    visualizacoes;
	 private String descricao;
	 private String imagem;
	 private String site;


	 public void setCodigo(int codigo) throws Exception
	 {
	    if (codigo <= 0)
	       throw new Exception("Codigo invalido");

	       this.codigo = codigo;
	 }
	 public void setVisualizacoes(int visualizacoes) throws Exception
	 {
	    if (codigo < 0)
	       throw new Exception("Codigo invalido");

	       this.visualizacoes = visualizacoes;
	 }

	 public void setNome(String nome) throws Exception
	 {
	     if (nome==null || nome.equals(""))
	         throw new Exception ("Nome não fornecido");

	     this.nome = nome;
	 }

	 public void setEmail(String email) throws Exception
	 {
	     if (email==null || email.equals(""))
	         throw new Exception ("Email não fornecido");

	     this.email = email;
	 }

	 public void setCnpj(String cnpj) throws Exception
	 {
	     if (cnpj==null || cnpj.equals(""))
	         throw new Exception ("CNPJ não fornecido");

	     this.cnpj = cnpj;
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

	 public void setTelefone(String telefone) throws Exception
	 {
	     if (telefone==null || telefone.equals("")) // formatação
	         throw new Exception ("Telefone não fornecido");

	     this.telefone = telefone;
	 }

	 public void setSite(String site) throws Exception
	 {
	     if (site==null || site.equals(""))
	         throw new Exception ("Site não fornecido");

	     this.site = site;
	 }

	 public void setDescricao(String desc) throws Exception
	 {
	     if (desc==null || desc.equals(""))
	         throw new Exception ("Descrição não fornecida");

	     this.descricao = desc;
	 }

	 public void setImagem(String img) throws Exception
	 {
	     if (img==null || img.equals(""))
	         throw new Exception ("Nome não fornecido");

	     this.imagem = img;
	 }

	 public String getImagem ()
	 {
	     return this.imagem;
	 }

	 public int getCodigo ()
	 {
	     return this.codigo;
	 }

	 public String getNome ()
	 {
	     return this.nome;
	 }

	 public int getVisualizacoes ()
	 {
	     return this.visualizacoes;
	 }

	 public String getEmail ()
	 {
	     return this.email;
	 }

	 public String getTelefone ()
	 {
	     return this.telefone;
	 }

	 public String getCnpj ()
	 {
	     return this.cnpj;
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

	 public String getSite ()
	 {
	     return this.site;
	 }

	 public String getDescricao ()
	 {
	     return this.descricao;
	 }

	 public Entidade(int codigo) throws Exception
	 {
	    this.setCodigo  (codigo);
	 }

	 public Entidade(int codigo, String nome, String cnpj, String endereco, String email, String telefone, String usuario,
			 String senha, int visualizacoes, String descricao, String imagem, String site) throws Exception
	 {
	    this.setCodigo       (codigo);
	    this.setNome         (nome);
	    this.setEmail        (email);
	    this.setEndereco     (endereco);
	    this.setUsuario      (usuario);
	    this.setSenha        (senha);
	    this.setCnpj         (cnpj);
	    this.setVisualizacoes(visualizacoes);
	    this.setSite         (site);
	    this.setDescricao    (descricao);
	    this.setImagem       (imagem);
	    this.setTelefone     (telefone);
	 }

	 public String toString ()
	 {
	     String ret="";

		 ret+="Codigo: "+this.codigo+"\n";
		 ret+="Nome: "+this.nome  +"\n";
		 ret+="Email: "+this.email + "\n";
		 ret+="CPNJ: " + this.cnpj +"\n";
		 ret+="Endereço: "+this.endereco + "\n";
		 ret+="Usuário: "+this.usuario + "\n";
		 ret+="Senha: "+this.senha + "\n";
		 ret+="Telefone: "+this.telefone +"\n";
		 ret+="Visualizacoes" + this.visualizacoes + "\n";
		 ret+="Descrição: "+this.descricao + "\n";
		 ret+="Site: "+this.site + "\n";

		 return ret;
	 }

	public int hashCode()
	{
		int ret = 1;
		ret = ret * 5 + new Integer (this.codigo).hashCode();
		ret = ret * 5 + this.nome.hashCode();
		ret = ret * 5 + this.email.hashCode();
		ret = ret * 5 + this.cnpj.hashCode();
		ret = ret * 5 + this.telefone.hashCode();
		ret = ret * 5 + this.endereco.hashCode();
		ret = ret * 5 + this.usuario.hashCode();
		ret = ret * 5 + this.senha.hashCode();
		ret = ret * 5 + this.site.hashCode();
		ret = ret * 5 + this.descricao.hashCode();
		ret = ret * 5 + new Integer (this.visualizacoes).hashCode();

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

		Entidade ent = (Entidade)obj;

		if(ent.codigo != this.codigo || !ent.nome.equals(this.nome) || !ent.email.equals(this.email) ||
		  !ent.cnpj.equals(this.cnpj) || !ent.telefone.equals(this.telefone) || !ent.endereco.equals(this.endereco) ||
		  !ent.usuario.equals(this.usuario) || !ent.senha.equals(this.senha) || ent.visualizacoes != this.visualizacoes ||
		  !ent.site.equals(this.site) || !ent.descricao.equals(this.descricao))
			return false;

		return true;
	}

	public Object clone()
	{
		Entidade ret = null;
		try
		{
			ret = new Entidade (this);
		}
		catch(Exception erro)
		{}
		return ret;
	}

	public Entidade(Entidade molde) throws Exception
	{
		if(molde == null)
			throw new Exception();

		this.codigo = molde.codigo;
		this.nome = molde.nome;
		this.email = molde.email;
		this.cnpj = molde.cnpj;
		this.telefone = molde.telefone;
		this.endereco = molde.endereco;
		this.usuario = molde.usuario;
		this.senha = molde.senha;
		this.visualizacoes = molde.visualizacoes;
		this.site = molde.site;
		this.descricao = molde.descricao;
		this.imagem = molde.imagem;
	}

	public int compareTo(Entidade outro)
	{
		if(this.codigo>outro.codigo)
			return 1;
		if(this.codigo<outro.codigo)
			return -1;
		return 0;
	}
}
