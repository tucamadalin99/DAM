package com.example.bilet2;

public class EnumConverter {
   // @TypeConverter
    public Pozitie EnumfromString(String value){
        if(value == null)
            return null;
        if(value.equals("portar"))
            return Pozitie.portar;
        else if(value.equals("fundas"))
            return Pozitie.fundas;
        else if(value.equals("mijlocas"))
            return Pozitie.mijlocas;
        else
            return  Pozitie.atacant;
    }

   // @TypeConverter
    public String StringToEnum(Pozitie poz){
        if(poz == null)
            return null;
        if(poz.equals(Pozitie.portar))
            return "portar";
        else if(poz.equals(Pozitie.fundas))
            return "fundas";
        else if(poz.equals(Pozitie.mijlocas))
            return "mijlocas";
        else
            return "atacant";
    }
}
