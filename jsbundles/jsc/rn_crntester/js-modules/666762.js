__d(function(g,r,i,a,m,e,d){'use strict';var o=r(d[0]).StyleSheet.create({box:{width:100,height:100,borderWidth:2},shadow1:{shadowOpacity:.5,shadowRadius:3,shadowOffset:{width:2,height:2}},shadow2:{shadowOpacity:1,shadowColor:'red',shadowRadius:0,shadowOffset:{width:3,height:3}},shadowShaped:{borderRadius:50},shadowImage:{borderWidth:0,overflow:'visible'},shadowChild:{backgroundColor:'transparent'},shadowChildBox:{width:80,height:80,borderRadius:40,margin:8,backgroundColor:'red'}});e.title='Box Shadow',e.description='Demonstrates some of the shadow styles available to Views.',e.examples=[{title:'Basic shadow',description:'shadowOpacity: 0.5, shadowOffset: {2, 2}',render:function(){return r(d[1]).createElement(r(d[0]).View,{style:[o.box,o.shadow1]})}},{title:'Colored shadow',description:"shadowColor: 'red', shadowRadius: 0",render:function(){return r(d[1]).createElement(r(d[0]).View,{style:[o.box,o.shadow2]})}},{title:'Shaped shadow',description:'borderRadius: 50',render:function(){return r(d[1]).createElement(r(d[0]).View,{style:[o.box,o.shadow1,o.shadowShaped]})}},{title:'Image shadow',description:'Image shadows are derived exactly from the pixels.',render:function(){return r(d[1]).createElement(r(d[0]).Image,{source:r(d[2]),style:[o.box,o.shadow1,o.shadowImage]})}},{title:'Child shadow',description:'For views without an opaque background color, shadow will be derived from the subviews.',render:function(){return r(d[1]).createElement(r(d[0]).View,{style:[o.box,o.shadow1,o.shadowChild]},r(d[1]).createElement(r(d[0]).View,{style:[o.box,o.shadowChildBox]}))}}]},666762,[516,514,666757]);