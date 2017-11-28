package cn.org.rapid_framework.generator;


public class GeneratorMain {
	/**
	 * 按以下步骤进行：
	 */
	public static void main(String[] args) throws Throwable {
		GeneratorFacade g = new GeneratorFacade();
        //TODO:步骤一：改模块名称，最终生成的顶java page以generator.xml的参数basepackage + moduleName为结果，例如com.gzl.b2c.proudct
        GeneratorProperties.setProperty("moduleName","shop");
        //TODO:步骤二：改dbSchema 要与generator.xml 参数jdbc.schema一致
        GeneratorProperties.setProperty("dbSchema","imall_cloudc");
//		g.printAllTableNames();				//打印数据库中的表名称

		g.deleteOutRootDir();							//删除生成器的输出目录

        //TODO:步骤三：填入要生成代码的表名称，可以多个表但须以 ',' 分开
      //  String tables = "mb_bank_transaction_log,mb_card_mgr,mb_card_setting,mb_level,mb_level_log,mb_member,mb_receiver_addr,mb_recharge_setting,mb_recharge_transaction_log,or_area,or_courier,or_order,or_order_item,or_shopping_cart_list,or_store,pd_product,pd_product_category,pd_product_category_link,pd_product_comment,pd_product_img,pd_product_material";

		String tables = "T_SHP_MEASURING_DEVICE_ACCOUNTS";
        for(String table : tables.split(",")){
           g.generateByTable(table,"template");
        }
//		g.generateByTable("table_name","template");	//通过数据库表生成文件,template为模板的根目录
		//g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//		g.generateByClass(Blog.class,"template_clazz");

//		g.deleteByTable("table_name", "template"); //删除生成的文件
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
