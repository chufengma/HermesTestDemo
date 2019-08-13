__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function l(){return r(d[1])(this,l),r(d[2])(this,r(d[3])(l).apply(this,arguments))}return r(d[0])(l,t),r(d[4])(l,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).Text,{style:{fontWeight:'bold',color:'#527fe4'}},this.props.children)}}]),l})(r(d[5]).Component),l=(function(t){function l(){var t,n;r(d[1])(this,l);for(var o=arguments.length,c=new Array(o),s=0;s<o;s++)c[s]=arguments[s];return(n=r(d[2])(this,(t=r(d[3])(l)).call.apply(t,[this].concat(c)))).state={fontWeight:'bold',fontSize:15},n.toggleWeight=function(){n.setState({fontWeight:'bold'===n.state.fontWeight?'normal':'bold'})},n.increaseSize=function(){n.setState({fontSize:n.state.fontSize+1})},n}return r(d[0])(l,t),r(d[4])(l,[{key:"render",value:function(){var t={fontWeight:this.state.fontWeight,fontSize:this.state.fontSize};return r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,{style:t},"Tap the controls below to change attributes."),r(d[5]).createElement(r(d[6]).Text,null,r(d[5]).createElement(r(d[6]).Text,null,"See how it will even work on",' ',r(d[5]).createElement(r(d[6]).Text,{style:t},"this nested text"))),r(d[5]).createElement(r(d[6]).Text,null,r(d[5]).createElement(r(d[6]).Text,{onPress:this.toggleWeight},"Toggle Weight"),' (with highlight onPress)'),r(d[5]).createElement(r(d[6]).Text,{onPress:this.increaseSize,suppressHighlighting:!0},"Increase Size (suppressHighlighting true)"))}}]),l})(r(d[5]).Component),n=(function(n){function c(){return r(d[1])(this,c),r(d[2])(this,r(d[3])(c).apply(this,arguments))}return r(d[0])(c,n),r(d[4])(c,[{key:"render",value:function(){return r(d[5]).createElement(r(d[7]),{title:"<Text>"},r(d[5]).createElement(r(d[8]),{title:"Wrap"},r(d[5]).createElement(r(d[6]).Text,null,"The text should wrap if it goes on multiple lines. See, this is going to the next line.")),r(d[5]).createElement(r(d[8]),{title:"Padding"},r(d[5]).createElement(r(d[6]).Text,{style:{padding:10}},"This text is indented by 10px padding on all sides.")),r(d[5]).createElement(r(d[8]),{title:"Text metrics legend"},r(d[5]).createElement(r(d[9]),null)),r(d[5]).createElement(r(d[8]),{title:"Font Family"},r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif'}},"Sans-Serif"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif',fontWeight:'bold'}},"Sans-Serif Bold"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'serif'}},"Serif"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'serif',fontWeight:'bold'}},"Serif Bold"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'monospace'}},"Monospace"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'monospace',fontWeight:'bold'}},"Monospace Bold (After 5.0)")),r(d[5]).createElement(r(d[8]),{title:"Android Material Design fonts"},r(d[5]).createElement(r(d[6]).View,{style:{flexDirection:'row',alignItems:'flex-start'}},r(d[5]).createElement(r(d[6]).View,{style:{flex:1}},r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif'}},"Roboto Regular"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif',fontStyle:'italic'}},"Roboto Italic"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif',fontWeight:'bold'}},"Roboto Bold"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif',fontStyle:'italic',fontWeight:'bold'}},"Roboto Bold Italic"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-light'}},"Roboto Light"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-light',fontStyle:'italic'}},"Roboto Light Italic"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-thin'}},"Roboto Thin (After 4.2)"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-thin',fontStyle:'italic'}},"Roboto Thin Italic (After 4.2)"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-condensed'}},"Roboto Condensed"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-condensed',fontStyle:'italic'}},"Roboto Condensed Italic"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-condensed',fontWeight:'bold'}},"Roboto Condensed Bold"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-condensed',fontStyle:'italic',fontWeight:'bold'}},"Roboto Condensed Bold Italic"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-medium'}},"Roboto Medium (After 5.0)"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif-medium',fontStyle:'italic'}},"Roboto Medium Italic (After 5.0)")))),r(d[5]).createElement(r(d[8]),{title:"Custom Fonts"},r(d[5]).createElement(r(d[6]).View,{style:{flexDirection:'row',alignItems:'flex-start'}},r(d[5]).createElement(r(d[6]).View,{style:{flex:1}},r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'notoserif'}},"NotoSerif Regular"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'notoserif',fontStyle:'italic',fontWeight:'bold'}},"NotoSerif Bold Italic"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'notoserif',fontStyle:'italic'}},"NotoSerif Italic (Missing Font file)")))),r(d[5]).createElement(r(d[8]),{title:"Font Size"},r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:23}},"Size 23"),r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:8}},"Size 8")),r(d[5]).createElement(r(d[8]),{title:"Color"},r(d[5]).createElement(r(d[6]).Text,{style:{color:'red'}},"Red color"),r(d[5]).createElement(r(d[6]).Text,{style:{color:'blue'}},"Blue color")),r(d[5]).createElement(r(d[8]),{title:"Font Weight"},r(d[5]).createElement(r(d[6]).Text,{style:{fontWeight:'bold'}},"Move fast and be bold"),r(d[5]).createElement(r(d[6]).Text,{style:{fontWeight:'normal'}},"Move fast and be bold")),r(d[5]).createElement(r(d[8]),{title:"Font Style"},r(d[5]).createElement(r(d[6]).Text,{style:{fontStyle:'italic'}},"Move fast and be bold"),r(d[5]).createElement(r(d[6]).Text,{style:{fontStyle:'normal'}},"Move fast and be bold")),r(d[5]).createElement(r(d[8]),{title:"Font Style and Weight"},r(d[5]).createElement(r(d[6]).Text,{style:{fontStyle:'italic',fontWeight:'bold'}},"Move fast and be bold")),r(d[5]).createElement(r(d[8]),{title:"Text Decoration"},r(d[5]).createElement(r(d[6]).Text,{style:{textDecorationLine:'underline'}},"Solid underline"),r(d[5]).createElement(r(d[6]).Text,{style:{textDecorationLine:'none'}},"None textDecoration"),r(d[5]).createElement(r(d[6]).Text,{style:{textDecorationLine:'line-through',textDecorationStyle:'solid'}},"Solid line-through"),r(d[5]).createElement(r(d[6]).Text,{style:{textDecorationLine:'underline line-through'}},"Both underline and line-through"),r(d[5]).createElement(r(d[6]).Text,null,"Mixed text with",' ',r(d[5]).createElement(r(d[6]).Text,{style:{textDecorationLine:'underline'}},"underline")," and",' ',r(d[5]).createElement(r(d[6]).Text,{style:{textDecorationLine:'line-through'}},"line-through"),' ',"text nodes")),r(d[5]).createElement(r(d[8]),{title:"Nested"},r(d[5]).createElement(r(d[6]).Text,{onPress:function(){}},"(Normal text,",r(d[5]).createElement(r(d[6]).Text,{style:{color:'red',fontWeight:'bold'}},"(R)red",r(d[5]).createElement(r(d[6]).Text,{style:{color:'green',fontWeight:'normal'}},"(G)green",r(d[5]).createElement(r(d[6]).Text,{style:{color:'blue',fontWeight:'bold'}},"(B)blue",r(d[5]).createElement(r(d[6]).Text,{style:{color:'cyan',fontWeight:'normal'}},"(C)cyan",r(d[5]).createElement(r(d[6]).Text,{style:{color:'magenta',fontWeight:'bold'}},"(M)magenta",r(d[5]).createElement(r(d[6]).Text,{style:{color:'yellow',fontWeight:'normal'}},"(Y)yellow",r(d[5]).createElement(r(d[6]).Text,{style:{color:'black',fontWeight:'bold'}},"(K)black"))))))),r(d[5]).createElement(r(d[6]).Text,{style:{fontWeight:'bold'},onPress:function(){}},"(and bold",r(d[5]).createElement(r(d[6]).Text,{style:{fontStyle:'italic',fontSize:11,color:'#527fe4'},onPress:function(){}},"(and tiny bold italic blue",r(d[5]).createElement(r(d[6]).Text,{style:{fontWeight:'normal',fontStyle:'normal'},onPress:function(){}},"(and tiny normal blue)"),")"),")"),")"),r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'serif'},onPress:function(){}},"(Serif",r(d[5]).createElement(r(d[6]).Text,{style:{fontStyle:'italic',fontWeight:'bold'},onPress:function(){}},"(Serif Bold Italic",r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'monospace',fontStyle:'normal',fontWeight:'normal'},onPress:function(){}},"(Monospace Normal",r(d[5]).createElement(r(d[6]).Text,{style:{fontFamily:'sans-serif',fontWeight:'bold'},onPress:function(){}},"(Sans-Serif Bold",r(d[5]).createElement(r(d[6]).Text,{style:{fontWeight:'normal'},onPress:function(){}},"(and Sans-Serif Normal)"),")"),")"),")"),")"),r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:12}},r(d[5]).createElement(t,null,"Entity Name"))),r(d[5]).createElement(r(d[8]),{title:"Text Align"},r(d[5]).createElement(r(d[6]).Text,null,"auto (default) - english LTR"),r(d[5]).createElement(r(d[6]).Text,null,"\u0623\u062d\u0628 \u0627\u0644\u0644\u063a\u0629 \u0627\u0644\u0639\u0631\u0628\u064a\u0629 auto (default) - arabic RTL"),r(d[5]).createElement(r(d[6]).Text,{style:{textAlign:'left'}},"left left left left left left left left left left left left left left left"),r(d[5]).createElement(r(d[6]).Text,{style:{textAlign:'center'}},"center center center center center center center center center center center"),r(d[5]).createElement(r(d[6]).Text,{style:{textAlign:'right'}},"right right right right right right right right right right right right right")),r(d[5]).createElement(r(d[8]),{title:"Unicode"},r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).View,{style:{flexDirection:'row'}},r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'red'}},"\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002")),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'red'}},"\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002")),r(d[5]).createElement(r(d[6]).View,{style:{alignItems:'center'}},r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'red'}},"\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002")),r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'red'}},"\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002\u661f\u9645\u4e89\u9738\u662f\u4e16\u754c\u4e0a\u6700\u597d\u7684\u6e38\u620f\u3002")))),r(d[5]).createElement(r(d[8]),{title:"Spaces"},r(d[5]).createElement(r(d[6]).Text,null,"A ",'generated'," ",'string'," and some \xa0\xa0\xa0 spaces")),r(d[5]).createElement(r(d[8]),{title:"Line Height"},r(d[5]).createElement(r(d[6]).Text,{style:{lineHeight:35}},"Holisticly formulate inexpensive ideas before best-of-breed benefits. ",r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:20}},"Continually")," expedite magnetic potentialities rather than client-focused interfaces.")),r(d[5]).createElement(r(d[8]),{title:"Letter Spacing"},r(d[5]).createElement(r(d[6]).View,null,r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:0}},"letterSpacing = 0"),r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:2,marginTop:5}},"letterSpacing = 2"),r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:9,marginTop:5}},"letterSpacing = 9"),r(d[5]).createElement(r(d[6]).View,{style:{flexDirection:'row'}},r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:12,letterSpacing:2,backgroundColor:'fuchsia',marginTop:5}},"With size and background color")),r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:-1,marginTop:5}},"letterSpacing = -1"),r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:3,backgroundColor:'#dddddd',marginTop:5}},"[letterSpacing = 3]",r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:0,backgroundColor:'#bbbbbb'}},"[Nested letterSpacing = 0]"),r(d[5]).createElement(r(d[6]).Text,{style:{letterSpacing:6,backgroundColor:'#eeeeee'}},"[Nested letterSpacing = 6]")))),r(d[5]).createElement(r(d[8]),{title:"Empty Text"},r(d[5]).createElement(r(d[6]).Text,null)),r(d[5]).createElement(r(d[8]),{title:"Toggling Attributes"},r(d[5]).createElement(l,null)),r(d[5]).createElement(r(d[8]),{title:"backgroundColor attribute"},r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'#ffaaaa'}},"Red background,",r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'#aaaaff'}},' ',"blue background,",r(d[5]).createElement(r(d[6]).Text,null,' ',"inherited blue background,",r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'#aaffaa'}},' ',"nested green background.")))),r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'rgba(100, 100, 100, 0.3)'}},"Same alpha as background,",r(d[5]).createElement(r(d[6]).Text,null,"Inherited alpha from background,",r(d[5]).createElement(r(d[6]).Text,{style:{backgroundColor:'rgba(100, 100, 100, 0.3)'}},"Reapply alpha")))),r(d[5]).createElement(r(d[8]),{title:"containerBackgroundColor attribute"},r(d[5]).createElement(r(d[6]).View,{style:{flexDirection:'row',height:85}},r(d[5]).createElement(r(d[6]).View,{style:{backgroundColor:'#ffaaaa',width:150}}),r(d[5]).createElement(r(d[6]).View,{style:{backgroundColor:'#aaaaff',width:150}})),r(d[5]).createElement(r(d[6]).Text,{style:[o.backgroundColorText,{top:-80}]},"Default containerBackgroundColor (inherited) + backgroundColor wash"),r(d[5]).createElement(r(d[6]).Text,{style:[o.backgroundColorText,{top:-70,backgroundColor:'transparent'}]},"containerBackgroundColor: 'transparent' + backgroundColor wash")),r(d[5]).createElement(r(d[8]),{title:"numberOfLines attribute"},r(d[5]).createElement(r(d[6]).Text,{numberOfLines:1},"Maximum of one line no matter now much I write here. If I keep writing it","'","ll just truncate after one line"),r(d[5]).createElement(r(d[6]).Text,{numberOfLines:2,style:{marginTop:20}},"Maximum of two lines no matter now much I write here. If I keep writing it","'","ll just truncate after two lines"),r(d[5]).createElement(r(d[6]).Text,{style:{marginTop:20}},"No maximum lines specified no matter now much I write here. If I keep writing it","'","ll just keep going and going")),r(d[5]).createElement(r(d[8]),{title:"allowFontScaling attribute"},r(d[5]).createElement(r(d[6]).Text,null,"By default, text will respect Text Size accessibility setting on Android. It means that all font sizes will be increased or decreased depending on the value of the Text Size setting in the OS's Settings app."),r(d[5]).createElement(r(d[6]).Text,{style:{marginTop:10}},"You can disable scaling for your Text component by passing ",'"',"allowFontScaling=",'{',"false",'}"'," prop."),r(d[5]).createElement(r(d[6]).Text,{allowFontScaling:!1,style:{marginTop:20,fontSize:15}},"This text will not scale.",' ',r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:15}},"This text also won't scale because it inherits \"allowFontScaling\" from its parent."))),r(d[5]).createElement(r(d[8]),{title:"selectable attribute"},r(d[5]).createElement(r(d[6]).Text,{selectable:!0},"This text is selectable if you click-and-hold, and will offer the native Android selection menus.")),r(d[5]).createElement(r(d[8]),{title:"selectionColor attribute"},r(d[5]).createElement(r(d[6]).Text,{selectable:!0,selectionColor:"orange"},"This text will have a orange highlight on selection.")),r(d[5]).createElement(r(d[8]),{title:"Inline images"},r(d[5]).createElement(r(d[6]).Text,null,"This text contains an inline image",' ',r(d[5]).createElement(r(d[6]).Image,{source:r(d[10])}),". Neat, huh?")),r(d[5]).createElement(r(d[8]),{title:"Text shadow"},r(d[5]).createElement(r(d[6]).Text,{style:{fontSize:20,textShadowOffset:{width:2,height:2},textShadowRadius:1,textShadowColor:'#00cccc'}},"Demo text shadow")),r(d[5]).createElement(r(d[8]),{title:"Ellipsize mode"},r(d[5]).createElement(r(d[6]).Text,{numberOfLines:1},"This very long text should be truncated with dots in the end."),r(d[5]).createElement(r(d[6]).Text,{ellipsizeMode:"middle",numberOfLines:1},"This very long text should be truncated with dots in the middle."),r(d[5]).createElement(r(d[6]).Text,{ellipsizeMode:"head",numberOfLines:1},"This very long text should be truncated with dots in the beginning."),r(d[5]).createElement(r(d[6]).Text,{ellipsizeMode:"clip",numberOfLines:1},"This very long text should be clipped and this will not be visible.")),r(d[5]).createElement(r(d[8]),{title:"Include Font Padding"},r(d[5]).createElement(r(d[6]).View,{style:{flexDirection:'row',justifyContent:'space-around',marginBottom:10}},r(d[5]).createElement(r(d[6]).View,{style:{alignItems:'center'}},r(d[5]).createElement(r(d[6]).Text,{style:o.includeFontPaddingText},"Ey"),r(d[5]).createElement(r(d[6]).Text,null,"Default")),r(d[5]).createElement(r(d[6]).View,{style:{alignItems:'center'}},r(d[5]).createElement(r(d[6]).Text,{style:[o.includeFontPaddingText,{includeFontPadding:!1,marginLeft:10}]},"Ey"),r(d[5]).createElement(r(d[6]).Text,null,"includeFontPadding: false"))),r(d[5]).createElement(r(d[6]).Text,null,"By default Android will put extra space above text to allow for upper-case accents or other ascenders. With some fonts, this can make text look slightly misaligned when centered vertically.")),r(d[5]).createElement(r(d[8]),{title:"Text transform"},r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'uppercase'}},"This text should be uppercased."),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'lowercase'}},"This TEXT SHOULD be lowercased."),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'capitalize'}},"This text should be CAPITALIZED."),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'capitalize'}},"Mixed: ",r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'uppercase'}},"uppercase "),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'lowercase'}},"LoWeRcAsE "),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'capitalize'}},"capitalize each word")),r(d[5]).createElement(r(d[6]).Text,null,"Should be \"ABC\":",r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'uppercase'}},"a",r(d[5]).createElement(r(d[6]).Text,null,"b"),"c")),r(d[5]).createElement(r(d[6]).Text,null,"Should be \"AbC\":",r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'uppercase'}},"a",r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'none'}},"b"),"c")),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'none'}},'.aa\tbb\t\tcc  dd EE \r\nZZ I like to eat apples. \n\u4e2d\u6587\xe9\xe9 \u6211\u559c\u6b22\u5403\u82f9\u679c\u3002awdawd   '),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'uppercase'}},'.aa\tbb\t\tcc  dd EE \r\nZZ I like to eat apples. \n\u4e2d\u6587\xe9\xe9 \u6211\u559c\u6b22\u5403\u82f9\u679c\u3002awdawd   '),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'lowercase'}},'.aa\tbb\t\tcc  dd EE \r\nZZ I like to eat apples. \n\u4e2d\u6587\xe9\xe9 \u6211\u559c\u6b22\u5403\u82f9\u679c\u3002awdawd   '),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'capitalize'}},'.aa\tbb\t\tcc  dd EE \r\nZZ I like to eat apples. \n\u4e2d\u6587\xe9\xe9 \u6211\u559c\u6b22\u5403\u82f9\u679c\u3002awdawd   '),r(d[5]).createElement(r(d[6]).Text,{style:{textTransform:'uppercase',fontSize:16,color:'turquoise',backgroundColor:'blue',lineHeight:32,letterSpacing:2,alignSelf:'flex-start'}},"Works with other text styles")))}}]),c})(r(d[5]).Component),o=r(d[6]).StyleSheet.create({backgroundColorText:{left:5,backgroundColor:'rgba(100, 100, 100, 0.3)'},includeFontPaddingText:{fontSize:120,fontFamily:'sans-serif',backgroundColor:'#EEEEEE',color:'#000000',textAlignVertical:'center',alignSelf:'center'}});e.title='<Text>',e.description='Base component for rendering styled text.',e.examples=[{title:'Basic text',render:function(){return r(d[5]).createElement(n,null)}}]},666811,[614,616,617,620,621,514,516,666697,666708,666736,666812]);