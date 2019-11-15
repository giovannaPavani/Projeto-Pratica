package bd.dbos;

public class Funcionario implements Cloneable
{
	private int codigo;
	private String nome;
	private String cpf;
	private String email;
	private float salario;
	private String telefone;
	private String cargo;
	private String conta;
	private String agencia;
	private String endereco;
	private String usuario;
	private String senha;

 	public void setCodigo (int cod) throws Exception
    {
        if (cod <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = cod;
    }   
 
 	public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }
 
 	public void setCpf (String cpf) throws Exception
    {
        if (cpf==null || cpf.equals(""))
            throw new Exception ("Cpf nao fornecido");

        this.cpf = cpf;
    }
 
	public void setEmail (String email) throws Exception
    {
        if (email==null || email.equals(""))
            throw new Exception ("Email nao fornecido");

        this.email = email;
    }
	
 	public void setSalario (float salario) throws Exception
    {
        if (salario <= 0)
            throw new Exception ("Salario invalido");

        this.salario = salario;
    }
 
 	public void setTelefone (String telefone) throws Exception
    {
        if (telefone==null || telefone.equals(""))
            throw new Exception ("Telefone nao fornecido");

        this.telefone = telefone;
    }

	public void setCargo (String cargo) throws Exception
    {
        if (cargo==null || cargo.equals(""))
            throw new Exception ("Cargo nao fornecido");

        this.cargo = cargo;
    }

	public void setConta (String conta) throws Exception
    {
        if (conta==null || conta.equals(""))
            throw new Exception ("Conta nao fornecida");

        this.conta = conta;
    }

	public void setAgencia (String agencia) throws Exception
    {
        if (agencia==null || agencia.equals(""))
            throw new Exception ("Agencia nao fornecida");

        this.agencia = agencia;
    }
	
	public void setEndereco (String endereco) throws Exception
    {
        if (endereco==null || endereco.equals(""))
            throw new Exception ("Endereco nao fornecido");

        this.endereco = endereco;
    }

	public void setUsuario (String usuario) throws Exception
    {
        if (usuario==null || usuario.equals(""))
            throw new Exception ("Usuario nao fornecido");

        this.usuario = usuario;
    }
	
	public void setSenha (String senha) throws Exception
    {
        if (senha==null || senha.equals(""))
            throw new Exception ("Senha nao fornecido");

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
 	
    public String getCpf ()
    {
        return this.cpf;
    }
    
    public String getEmail ()
    {
        return this.email;
    }

    public float getSalario ()
    {
        return this.salario;
    }
    
    public String getTelefone ()
    {
        return this.telefone;
    }
    
	public String getCargo ()
    {
        return this.cargo;
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
	
	public Funcionario (int cod, String nome,String cpf,String email,float salario,String telefone,String cargo,String conta,String agencia,String endereco,String usuario,String senha) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
		this.setCpf    (cpf);
		this.setEmail    (email);
		this.setSalario    (salario);
		this.setTelefone    (telefone);
        this.setCargo  (cargo);
		this.setConta    (conta);
		this.setAgencia    (agencia);
		this.setEndereco    (endereco);
		this.setUsuario    (usuario);
		this.setSenha    (senha);
    }
	
	public String toString ()
    {
        String ret="";

        ret+="Codigo: "+this.codigo+"\n";
        ret+="Nome: "+this.nome  +"\n";
        ret+="Cpf: "+this.cpf  +"\n";
		ret+="Email: "+this.email  +"\n";
		ret+="Salario: "+this.salario  +"\n";	
		ret+="Telefone: "+this.telefone  +"\n";
		ret+="Cargo: "+this.cargo  +"\n";
		ret+="Conta: "+this.conta  +"\n";
		ret+="Agencia: "+this.agencia  +"\n";
		ret+="Endereco: "+this.endereco  +"\n";
		ret+="Usuario: "+this.usuario  +"\n";
		ret+="Senha: "+this.senha  +"\n";
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
		
		Funcionario fun = (Funcionario)obj;
		
		if(fun.codigo != this.codigo || fun.nome != this.nome || fun.cargo != this.cargo || fun.email != this.email || fun.cpf != this.cpf || fun.telefone != this.telefone || fun.conta != this.conta || fun.agencia != this.agencia || fun.endereco != this.endereco || fun.usuario != this.usuario || fun.senha != this.senha || fun.salario != this.salario)
			return false;
		
		return true;
	}
	
	public Object clone()
	{
		Funcionario ret = null;
		try
		{
			ret = new Funcionario (this);
		}
		catch(Exception erro)
		{} 
		return ret;
	}
	
	public Funcionario(Funcionario molde) throws Exception
	{
		if(molde == null)
			throw new Exception();
		
		this.codigo = molde.codigo;
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

 