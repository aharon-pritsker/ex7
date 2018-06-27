public class VariableFactory {

    public static Variable MakeVariable(String type, String name, String value,boolean finalFlag)throws Exception{
        if(type == null) {
            return null;
        } else if(type.equals("int")){
            return new IntVariable(name, value);
        } else if(type.equals("double")) {
            return new DoubleVariable(name, value);
        } else if(type.equals("boolean")) {
            return new BooleanVariable(name, value);
        } else if(type.equals("char")) {
            return new CharVariable(name, value);
        } else if(type.equals("String")) {
            return new StringVariable(name, value);
        }
        throw new Exception();
    }
}
