__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(){var t,o;r(d[1])(this,n);for(var l=arguments.length,c=new Array(l),s=0;s<l;s++)c[s]=arguments[s];return(o=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(c)))).handleClick=function(){r(d[4]).Linking.canOpenURL(o.props.url).then(function(t){t&&r(d[4]).Linking.openURL(o.props.url)})},o}return r(d[0])(n,t),r(d[5])(n,[{key:"render",value:function(){return r(d[6]).createElement(r(d[4]).TouchableOpacity,{onPress:this.handleClick},r(d[6]).createElement(r(d[4]).View,{style:o.button},r(d[6]).createElement(r(d[4]).Text,{style:o.text},"Open ",this.props.url)))}}]),n})(r(d[6]).Component),n=((function(t){function n(){var t,o;r(d[1])(this,n);for(var l=arguments.length,c=new Array(l),s=0;s<l;s++)c[s]=arguments[s];return(o=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(c)))).handleIntent=function(){return r(d[7]).async(function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,r(d[7]).awrap(r(d[4]).Linking.sendIntent(o.props.action,o.props.extras));case 3:t.next=8;break;case 5:t.prev=5,t.t0=t.catch(0),r(d[4]).ToastAndroid.show(t.t0.message,r(d[4]).ToastAndroid.LONG);case 8:case"end":return t.stop()}},null,null,[[0,5]])},o}r(d[0])(n,t),r(d[5])(n,[{key:"render",value:function(){return r(d[6]).createElement(r(d[4]).TouchableOpacity,{onPress:this.handleIntent},r(d[6]).createElement(r(d[4]).View,{style:[o.button,o.buttonIntent]},r(d[6]).createElement(r(d[4]).Text,{style:o.text},this.props.action)))}}])})(r(d[6]).Component),(function(n){function o(){return r(d[1])(this,o),r(d[2])(this,r(d[3])(o).apply(this,arguments))}return r(d[0])(o,n),r(d[5])(o,[{key:"render",value:function(){return r(d[6]).createElement(r(d[4]).View,null,r(d[6]).createElement(r(d[8]),{title:"Open external URLs"},r(d[6]).createElement(t,{url:'https://www.facebook.com'}),r(d[6]).createElement(t,{url:'http://www.facebook.com'}),r(d[6]).createElement(t,{url:'http://facebook.com'}),r(d[6]).createElement(t,{url:'fb://notifications'}),r(d[6]).createElement(t,{url:'geo:37.484847,-122.148386'}),r(d[6]).createElement(t,{url:'tel:9876543210'})),!1)}}]),o})(r(d[6]).Component)),o=r(d[4]).StyleSheet.create({button:{padding:10,backgroundColor:'#3B5998',marginBottom:10},buttonIntent:{backgroundColor:'#009688'},text:{color:'white'},textSeparator:{paddingBottom:8}});e.title='Linking',e.description='Shows how to use Linking to open URLs.',e.examples=[{title:'Simple list of items',render:function(){return r(d[6]).createElement(n,null)}}]},666773,[614,616,617,620,516,621,514,682,666708]);