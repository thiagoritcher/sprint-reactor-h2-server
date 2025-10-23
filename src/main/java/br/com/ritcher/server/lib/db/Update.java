package br.com.ritcher.server.lib.db;
public class Update {

	private DDLDatabaseUpdate conn;

	public Update(DDLDatabaseUpdate ddlDatabaseUpdate) {
		this.conn = ddlDatabaseUpdate;
	}

	public void v1() {
		conn.execute("create table sis_aplicacao(" + "	id int," + "	versao int," + "	criacao timestamp,"
				+ "	nome varchar(100)," + "	primary key(id)" + ");");
	}

	public void v2() {
		conn.execute("create table seg_usuario("
				+ "id bigint,"
				+ "versao int,"
				+ "nome varchar(254),"
				+ "email varchar(254)"
				+ ")");
	}
}