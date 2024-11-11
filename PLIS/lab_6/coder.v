module coder
	(input wire [5:0] data,
	output wire [6:0] seg_0,
	output wire [6:0] seg_1,
	output wire [6:0] seg_2,
	output wire [6:0] seg_3);

reg [6:0] out_0,
	  out_1,
	  out_2,
	  out_3;

assign seg_0=out_0;
assign seg_1=out_1;
assign seg_2=out_2;
assign seg_3=out_3;

always @*
case(data)
	6'b010000: begin  // _
		out_0 = 7'b1111111;
		out_1 = 7'b1111111;
		out_2 = 7'b1111111;
		out_3 = 7'b1110111;
		end
	6'b100000: begin // read
		out_0 = 7'b1001100; 
		out_1 = 7'b0000110; 
		out_2 = 7'b0001000; 
		out_3 = 7'b0100001; 
		end
	6'b110000: begin // go
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1000010; 
		out_3 = 7'b0100011;
		end
	6'b000001: begin // 1
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b1001111;
		end
	6'b000010: begin // 2
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b0010010;
		end
	6'b000011: begin // 3
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b0000110;
		end
	6'b000100: begin // 4
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b1001100;
		end
	6'b000101: begin // 5
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b0100100;
		end
	6'b000110: begin // 6
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b0000010;
		end
	6'b000111: begin // 7
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b1111000;
		end
	6'b001000: begin // 8
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b0000000;
		end
	6'b001001: begin // 9
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1111111; 
		out_3 = 7'b0010000;
		end
	6'b001010: begin // 10
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1001111; 
		out_3 = 7'b1000000;
		end
	6'b001011: begin // 11
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1001111; 
		out_3 = 7'b1001111;
		end
	6'b001100: begin // 12
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1001111; 
		out_3 = 7'b0010010;
		end
	6'b001101: begin // 13
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1001111; 
		out_3 = 7'b0000110;
		end
	6'b001110: begin // 14
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1001111; 
		out_3 = 7'b1001100;
		end
	6'b001111: begin // 15
		out_0 = 7'b1111111; 
		out_1 = 7'b1111111; 
		out_2 = 7'b1001111; 
		out_3 = 7'b0100100;
		end

endcase
endmodule
