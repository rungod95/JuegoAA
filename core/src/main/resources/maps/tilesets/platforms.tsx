<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="platforms" tilewidth="32" tileheight="32" tilecount="13" columns="4">
 <image source="../../textures/tilesets/platforms.png" width="128" height="104"/>
 <tile id="0" type="normal">
  <properties>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="1" type="normal">
  <properties>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="2" type="normal">
  <properties>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="3" type="normal">
  <properties>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="4" type="moving">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="speed" type="float" value="2.0"/>
   <property name="distance" type="float" value="4.0"/>
  </properties>
 </tile>
 <tile id="5" type="moving">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="speed" type="float" value="3.0"/>
   <property name="distance" type="float" value="6.0"/>
  </properties>
 </tile>
 <tile id="6" type="breakable">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="breakTime" type="float" value="1.0"/>
  </properties>
 </tile>
 <tile id="7" type="breakable">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="breakTime" type="float" value="0.5"/>
  </properties>
 </tile>
 <tile id="8" type="breakable">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="breakTime" type="float" value="0.25"/>
  </properties>
 </tile>
 <tile id="9" type="disappearing">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="disappearTime" type="float" value="1.0"/>
   <property name="reappearTime" type="float" value="2.0"/>
  </properties>
 </tile>
 <tile id="10" type="disappearing">
  <properties>
   <property name="solid" type="bool" value="true"/>
   <property name="disappearTime" type="float" value="0.5"/>
   <property name="reappearTime" type="float" value="1.0"/>
  </properties>
 </tile>
 <tile id="11" type="spikes">
  <properties>
   <property name="damage" type="float" value="10.0"/>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="12" type="spikes">
  <properties>
   <property name="damage" type="float" value="20.0"/>
   <property name="solid" type="bool" value="true"/>
  </properties>
 </tile>
</tileset> 