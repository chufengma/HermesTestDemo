__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function c(){var t,n,o;r(d[1])(this,c);for(var l=arguments.length,u=new Array(l),s=0;s<l;s++)u[s]=arguments[s];return(o=r(d[2])(this,(t=r(d[3])(c)).call.apply(t,[this].concat(u)))).state={dataSource:(n=r(d[4]).SwipeableListView.getNewDataSource()).cloneWithRowsAndSections.apply(n,r(d[5])(o._genDataSource({})))},o._pressData={},o}return r(d[0])(c,t),r(d[6])(c,[{key:"render",value:function(){return r(d[7]).createElement(r(d[8]),{title:this.props.navigator?null:'<SwipeableListView>',noSpacer:!0,noScroll:!0},r(d[7]).createElement(r(d[4]).SwipeableListView,{dataSource:this.state.dataSource,maxSwipeDistance:100,renderQuickActions:function(t,n,o){return r(d[7]).createElement(r(d[4]).View,{style:u.actionsContainer},r(d[7]).createElement(r(d[4]).TouchableHighlight,{onPress:function(){r(d[4]).Alert.alert('Tips','You could do something with this row: '+t.text)}},r(d[7]).createElement(r(d[4]).Text,null,"Remove")))},renderRow:this._renderRow,renderSeparator:this._renderSeperator}))}},{key:"_renderRow",value:function(t,c,s){var p=Math.abs(l(t.id)),h=n[p%n.length];return r(d[7]).createElement(r(d[4]).TouchableHighlight,{onPress:function(){}},r(d[7]).createElement(r(d[4]).View,null,r(d[7]).createElement(r(d[4]).View,{style:u.row},r(d[7]).createElement(r(d[4]).Image,{style:u.thumb,source:h}),r(d[7]).createElement(r(d[4]).Text,{style:u.text},t.id+' - '+o.substr(0,p%301+10)))))}},{key:"_genDataSource",value:function(t){var n={},o=['Section 0'],l=[[]];n['Section 0']={};for(var u=0;u<100;u++){var c=t[u]?' (pressed)':'';n[o[0]]['Row '+u]={id:'Row '+u,text:'Row '+u+c},l[0].push('Row '+u)}return[n,o,l]}},{key:"_renderSeperator",value:function(t,n,o){return r(d[7]).createElement(r(d[4]).View,{key:t+"-"+n,style:{height:o?4:1,backgroundColor:o?'#3B5998':'#CCCCCC'}})}}]),c})(r(d[7]).Component),n=[r(d[9]),r(d[10]),r(d[11]),r(d[12]),r(d[13]),r(d[14]),r(d[15]),r(d[16]),r(d[17]),r(d[18]),r(d[19]),r(d[20])],o=['Lorem ipsum dolor sit amet, ius ad pertinax oportere accommodare, an vix ','civibus corrumpit referrentur. Te nam case ludus inciderint, te mea facilisi ','adipiscing. Sea id integre luptatum. In tota sale consequuntur nec. Erat ','ocurreret mei ei. Eu paulo sapientem vulputate est, vel an accusam ','intellegam interesset. Nam eu stet pericula reprimique, ea vim illud modus, ','putant invidunt reprehendunt ne qui.'].join(''),l=function(t){for(var n=15,o=t.length-1;o>=0;o--)n=(n<<5)-n+t.charCodeAt(o);return n},u=r(d[4]).StyleSheet.create({row:{flexDirection:'row',justifyContent:'center',padding:10,backgroundColor:'#F6F6F6'},thumb:{width:64,height:64},text:{flex:1},actionsContainer:{flex:1,flexDirection:'row',justifyContent:'flex-end',alignItems:'center'}});e.title='<SwipeableListView>',e.description='Performant, scrollable, swipeable list of data.',e.examples=[{title:'Simple swipable list',render:function(){return r(d[7]).createElement(t,null)}}]},666733,[614,616,617,620,516,636,621,514,666697,666685,666686,666687,666688,666689,666690,666691,666692,666693,666694,666695,666696]);