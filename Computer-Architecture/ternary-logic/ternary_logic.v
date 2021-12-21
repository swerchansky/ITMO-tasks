module ternary_max(a0, a1, b0, b1, out0, out1);
  input a0, a1, b0, b1;
  output out0, out1;
  
  wire or0_out, and_out, xor_out;
  wire out_w;

  
  supply1 power;
  supply0 ground;
  
  my_or or0_switch(a0, b0, or0_out);
  my_or or1_switch(a1, b1, out1);
  my_and and_switch(or0_out, out1, and_out);
  my_xor xor_switch(or0_out, and_out, out0);
  
endmodule

module ternary_consensus(a0, a1, b0, b1, out0, out1);
  input a0, a1, b0, b1;
  output out0, out1;

  wire mor, xor_0;
  
  supply1 power;
  supply0 ground;
  
  my_or moro(a0, b0, mor);
  my_and mand(a1, b1, out1);
  my_xor xoro(a1, b1, xor_0);
  my_or orout(mor, xor_0, out0);
  
endmodule

module my_not(a, out);
  input a;
  output out;
  
  supply1 power;
  supply0 ground;
  
  nmos n0(out, ground, a);
  pmos p0(out, power, a);
  
endmodule

module my_and(a, b, out);
  input a, b;
  output out;
  supply1 power;
  supply0 ground;
  
  wire w, out_w;
  
  pmos p0(out_w, power, a);
  pmos p1(out_w, power, b);
  nmos n1(w, ground, b);
  nmos n0(out_w, w, a);
  
  my_not not_switch(out_w, out);
  
endmodule

module my_or(a, b, out);
  input a, b;
  output out;
  supply1 power;
  supply0 ground;
  
  wire w, out_w;
  
  pmos p0(w, power, a);
  pmos p1(out_w, w, b);
  nmos n1(out_w, ground, b);
  nmos n0(out_w, ground, a);
  
  my_not not_switch(out_w, out);
  
endmodule


module my_xor(a, b, out);
  input a, b;
  output out;
  
  wire not0_w, notand_w, ornot_w, xoror;
  
  supply1 power;
  supply0 ground;
  
  my_not nota_switch(a, not0_w);
  my_not notb_switc(b, notand_w);
  my_or ornot_switch(not0_w, notand_w, ornot_w);
  my_or or_switch(a, b, xoror);
  my_and andor_out(xoror, ornot_w, out);
  
endmodule
