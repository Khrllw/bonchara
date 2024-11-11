module vmeste
	(input clk_all, reset_all, run_all,
	 input [3:0] balance_all,
	 output [6:0] seg_0,
	 output [6:0] seg_1,
	 output [6:0] seg_2,
	 output [6:0] seg_3); 

wire clk_div;

wire [5:0] out_all;

count_div count_div (.clk(clk_all),
		     .sync(clk_div));

conds conds (.clk(clk_div),
		     .run(run_all),
		     .reset(reset_all),
		     .balance(balance_all),
		     .out(out_all));

coder coder (.data(out_all), 
		.seg_0(seg_0),
		.seg_1(seg_1),
		.seg_2(seg_2),
		.seg_3(seg_3));

endmodule

